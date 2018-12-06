package platform.cars.service;


import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

public interface IUserService {

    UserInfo findUserInfoByAccount(String account);

    User checkIn(User user);
}
