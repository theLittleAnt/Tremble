package platform.cars.service;


import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    User checkIn(User user);

    boolean checkToken(String authToken) throws ParseException;

    int register(User user);

    int updatePwd(User user);

    int updateToken(User user);

    int updateUserInfo(UserInfo userInfo);

    UserInfo findUserInfoByAccount(String account);

    List<UserInfo> findSellerList();
}
