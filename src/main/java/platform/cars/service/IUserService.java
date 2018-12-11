package platform.cars.service;


import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    User checkIn(User user);

    boolean checkToken(String authToken) throws ParseException;

    boolean register(User user);

    boolean updatePwd(User user);

    boolean updateType(String authToken);

    boolean updateToken(User user);

    boolean updateUserInfo(UserInfo userInfo,String authToken);

    UserInfo findUserInfoByAccount(String account);

    User findUserByToken(String authToken);

    List<UserInfo> findSellerList();
}
