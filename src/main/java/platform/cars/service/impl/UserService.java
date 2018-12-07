package platform.cars.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import platform.cars.dao.IUserDao;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;
import platform.cars.utils.CommonUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private CommonUtils commonUtils;
    /**
     * 获取登录的token信息
     * 验证token是否过期，
     * 如果过期则重新获取token,并记录生成时间
     * 存入user对象，更新到数据库，
     * 返回token值
     *
     * @param user
     * @return token字符串
     */
    @Override
    public User checkIn(User user) {
        User checkedUser = userDao.checkIn(user);
        String token = null;
        if(null!=checkedUser){
            token = checkedUser.getAuthToken();
            try {
                if(!checkToken(token)){
                    token = commonUtils.genAuthToken();
                    user.setAuthToken(token);
                    user.setTokenGenTime(commonUtils.dateToStr(new Date()));
                    userDao.updateToken(user);
                    checkedUser.setAuthToken(token);
                }
            } catch (ParseException e) {
                e.printStackTrace();
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
    public boolean checkToken(String authToken) throws ParseException {
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
    public int register(User user) {
        user.setAuthToken(commonUtils.genAuthToken());
        user.setTokenGenTime(commonUtils.dateToStr(new Date()));
        return userDao.register(user);
    }

    /**
     * 修改用户密码
     * @param user
     * @return 更新条数
     */
    @Override
    public int updatePwd(User user) {
        return userDao.updatePwd(user);
    }

    /**
     * 更新token值
     * @param user
     * @return
     */
    @Override
    public int updateToken(User user) {
        return userDao.updateToken(user);
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @return 更新条数
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userDao.updateUserInfo(userInfo);
    }

    /**
     * 根据用户账号获取用户信息
     * @param account
     * @return 用户信息对象
     */
    @Override
    public UserInfo findUserInfoByAccount(String account){
        return userDao.findUserInfoByAccount(account);
    }

    /**
     * 返回待审核的卖家信息
     * @return
     */
    @Override
    public List<UserInfo> findSellerList() {
        return userDao.findSellerList();
    }
}
