package platform.cars.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.cars.dao.IUserDao;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserInfo findUserInfoByAccount(String account){
       return userDao.findUserInfoByAccount(account);
    }

    @Override
    public User checkIn(User user) {
        return userDao.checkIn(user);
    }


}
