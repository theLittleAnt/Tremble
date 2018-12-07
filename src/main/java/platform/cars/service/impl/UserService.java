package platform.cars.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.cars.dao.IUserDao;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;

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
