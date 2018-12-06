package platform.cars.dao;

import platform.cars.domain.User;
import platform.cars.domain.UserInfo;


public interface IUserDao {

    UserInfo findUserInfoByAccount(String account);

    User checkIn(User user);
}
