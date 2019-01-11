package platform.cars.dao;

import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.util.List;


public interface IUserDao {

    User checkIn(User user);

    User checkToken(String authToken);

    int saveUser(User user);

    int updatePwd(User user);

    int updateType(String account);

    int updateToken(User user);

    User findUserByToken(String authToken);

    int saveUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    UserInfo findUserInfoByAccount(String account);

    List<UserInfo> findSellerList();

    List<UserInfo> findSellerRequestList();

    UserInfo findUserInfoById(String userId);
}
