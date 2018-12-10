package platform.cars.service;


import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    User checkIn(User user);

    boolean checkToken(String authToken) throws ParseException;

    int register(User user);

    boolean updatePwd(User user);

    int updateToken(User user);

    boolean updateUserInfo(UserInfo userInfo,String authToken);

    UserInfo findUserInfoByAccount(String account);

    User findUserInfoByToken(String authToken);

    List<UserInfo> findSellerList();
}
