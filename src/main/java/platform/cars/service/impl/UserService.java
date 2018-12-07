package platform.cars.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import platform.cars.dao.IUserDao;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 登陆
     * @param user
     * @return 登陆的用户信息
     */
    @Override
    public User checkIn(User user) {
        return userDao.checkIn(user);
    }

    /**
     * 根据传入的token检查token值是否存在
     * 如果token不存在，返回false
     * 如果token存在，但是过期，重新生成token，返回false
     * 如果token存在并且没有过期,返回true
     * @param authToken
     * @return
     */
    @Override
    public boolean checkToken(String authToken) throws ParseException {
        boolean tag = false;
        String authGenTimeStr = userDao.checkToken(authToken).getAuthGenTime();

        if(authGenTimeStr!=null){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date authGenTime = sdf.parse(authGenTimeStr);

            if(date.getTime()-authGenTime.getTime()<30*60*1000){//毫秒级
                tag=true;
            }
        }
        return tag;
    }

    /**
     * 注册
     * @param user
     * @return 更新条数
     */
    @Override
    public int register(User user) {
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
