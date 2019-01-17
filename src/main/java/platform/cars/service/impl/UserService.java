package platform.cars.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.dao.IUserDao;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;
import platform.cars.utils.CommonUtils;
import platform.cars.utils.FileUtils;

import java.util.Date;
import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private CommonUtils commonUtils;

    @Value("${web.upload-path}")
    private String path;
    /**
     * 获取登录的token信息
     * 验证token是否过期，
     * 如果过期则重新获取token,并记录生成时间
     * 存入user对象，更新到数据库，
     * 返回token值
     * @param user
     * @return token字符串
     */
    @Override
    public User checkIn(User user) {
        User checkedUser = userDao.checkIn(user);
        if(null!=checkedUser){
            String token = checkedUser.getAuthToken();
            if(!checkToken(token)){
                token = commonUtils.genAuthToken();
                user.setAuthToken(token);
                user.setTokenGenTime(commonUtils.dateToStr(new Date()));
                userDao.updateToken(user);
                checkedUser.setAuthToken(token);
            }
        }
        return checkedUser;
    }

    /**
     * 根据传入的token检查token值是否存在
     * 如果token不存在，返回false
     * 如果token存在，但是过期，返回false
     * 如果token存在并且没有过期,返回true
     * @param authToken
     * @return
     */
    @Override
    public boolean checkToken(String authToken){
        boolean tag = false;
        User user =  userDao.checkToken(authToken);
        if(null!=user){
            String tokenGenTimeStr = user.getTokenGenTime();
            if(null!=tokenGenTimeStr && !StringUtils.isEmpty(tokenGenTimeStr)){
                Date date = new Date();
                Date tokenGenTime = commonUtils.strToDate(tokenGenTimeStr);
                if(date.getTime()-tokenGenTime.getTime()<30*60*1000){//毫秒级
                    tag=true;
                }
            }
        }
        return tag;
    }

    /**
     * 生成token以及token更新时间
     * 传入用户对象
     * 保存用户信息
     * @param user
     * @return 更新条数
     */
    @Override
    @Transactional
    public boolean register(User user) {
        boolean tag = false;
        user.setAuthToken(commonUtils.genAuthToken());
        user.setTokenGenTime(commonUtils.dateToStr(new Date()));
        if(userDao.saveUser(user)>0&&userDao.saveUserInfo(user.getAccount())>0){
            tag=true;
        }
        return tag;
    }

    /**
     * 根据token修改用户密码
     * @param user
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updatePwd(User user) {
        boolean result = false;
        if(!StringUtils.isEmpty(user.getPwd())){
            if(userDao.updatePwd(user)>0){
                result=true;
            }
        }
        return result;
    }

    /**
     * 修改用户的类型
     * @param account
     * @return
     */
    @Override
    @Transactional
    public boolean updateType(String account) {
        return userDao.updateType(account)>0?true:false;
    }

    /**
     * 撤销用户申请
     * @param account
     * @return
     */
    @Override
    public boolean updateQualification(String account) {
        UserInfo userInfo = userDao.findUserInfoById(account);
        if(!StringUtils.isEmpty(userInfo.getQualifications())){
            String fileName = userInfo.getQualifications();
            fileName=fileName.substring(fileName.lastIndexOf("/"));
            fileUtils.dropFile(path+fileName);
        }
        return userDao.updateQualification(account)>0?true:false;
    }

    /**
     * 更新token值
     * @param user
     * @return
     */
    @Override
    @Transactional
    public boolean updateToken(User user) {
        return userDao.updateToken(user)>0?true:false;
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateUserInfo(UserInfo userInfo,String authToken) {
        boolean result = false;
        User user = userDao.findUserByToken(authToken);
        if(null!=user && !StringUtils.isEmpty(user.getAccount())){
            userInfo.setAccount(user.getAccount());
            result = userDao.updateUserInfo(userInfo)>0?true:false;
        }
        return result;
    }

    /**
     * 根据authToken获取用户信息
     * @param authToken
     * @return 用户信息对象
     */
    @Override
    public UserInfo findUserInfoByAuthToken(String authToken){
        User user = userDao.findUserByToken(authToken);
        UserInfo userInfo = null;
        if(null!=user){
            userInfo = userDao.findUserInfoByAccount(user.getAccount());
        }
        return userInfo;
    }

    /**
     * 根据传入的token获取用户信息
     * @param authToken
     * @return
     */
    @Override
    public User findUserByToken(String authToken) {
        return userDao.findUserByToken(authToken);
    }

    /**
     * 根据卖家ID获取卖家信息
     * @param userId
     * @return
     */
    @Override
    public UserInfo findUserInfoById(String userId) {
        return userDao.findUserInfoById(userId);
    }


    /**
     * 返回待审核的卖家信息
     * @return
     */
    @Override
    public List<UserInfo> findSellerList() {
        return userDao.findSellerList();
    }

    /**
     * 获取申请资格不是空的用户
     * @return
     */
    @Override
    public List<UserInfo> findSellerRequestList() {
        return userDao.findSellerRequestList();
    }

    /**
     * 上传卖家资质
     * @param file
     * @param authToken
     * @return
     */
    @Override
    @Transactional
    public boolean uploadQualification(MultipartFile file,String authToken) {
        boolean result = false;
        if(null!=file){
            User user = userDao.findUserByToken(authToken);
            if(null!=user){
                String account = user.getAccount();
                String filePath = path;
                String fileName = fileUtils.picFileName(file,account+"_q");
                try {
                    fileUtils.uploadFile(file.getBytes(),filePath,fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                UserInfo userInfo = new UserInfo();
                userInfo.setAccount(account);
                userInfo.setQualifications("/cars-sale/static/"+fileName);
                if(userDao.updateUserInfo(userInfo)>0){
                    result = true;
                }
            }
        }
        return result;
    }
}
