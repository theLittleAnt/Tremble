package platform.cars.service;


import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.util.List;

public interface IUserService {

    User checkIn(User user);

    int register(User user);

    int updatePwd(User user);

    int updateToken(User user);

    int updateUserInfo(UserInfo userInfo);

    UserInfo findUserInfoByAccount(String account);

    List<UserInfo> findSellerList();
}
