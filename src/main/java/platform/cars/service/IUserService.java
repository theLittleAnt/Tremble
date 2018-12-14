package platform.cars.service;


import org.springframework.web.multipart.MultipartFile;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;

import java.text.ParseException;
import java.util.List;

public interface IUserService {

    User checkIn(User user);

    boolean checkToken(String authToken);

    boolean register(User user);

    boolean updatePwd(User user);

    boolean updateType(String account);

    boolean updateToken(User user);

    boolean updateUserInfo(UserInfo userInfo,String authToken);

    UserInfo findUserInfoByAuthToken(String authToken);

    User findUserByToken(String authToken);

    List<UserInfo> findSellerList();

    List<UserInfo> findSellerRequestList();

    boolean uploadQualification(MultipartFile file,String authToken);

}
