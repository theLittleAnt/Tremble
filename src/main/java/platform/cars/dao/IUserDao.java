package platform.cars.dao;

import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.util.List;


public interface IUserDao {

    String checkIn(User user);

    User checkToken(String authToken);

    int register(User user);

    int updatePwd(User user);

    int updateToken(User user);

    int updateUserInfo(UserInfo userInfo);

    UserInfo findUserInfoByAccount(String account);

    List<UserInfo> findSellerList();
}
