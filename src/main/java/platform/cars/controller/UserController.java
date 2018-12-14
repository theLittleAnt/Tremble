package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.anotation.DataCheckAnotation;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 根据登录的账号密码返回消息，
     * 带回token信息和用户类型
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login(User user) {
        Map<String,Object> map = new HashMap<>();
        String msg = "fail";
        User checkedUser = userService.checkIn(user);
        if(null!=checkedUser){
            msg = "success";
        }
        map.put("msg",msg);
        map.put("user",checkedUser);
        return map;
    }

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user){
        String msg = "fail";
        if(userService.register(user)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 获取卖家信息列表
     * @param authToken
     * @return
     */
    @RequestMapping("/sellers")
    @DataCheckAnotation
    public List<UserInfo> getSellerList(String authToken){
        return userService.findSellerList();
    }

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    @RequestMapping("alter-pwd")
    @DataCheckAnotation
    public String alterPwd(User user,String authToken){
        String msg = "fail";
        if(userService.updatePwd(user)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("alter-userinfo")
    @DataCheckAnotation
    public String alterUserInfo(UserInfo userInfo,String authToken){
        String msg = "fail";
        if(userService.updateUserInfo(userInfo,authToken)){
            msg="success";
        }
        return msg;
    }

    /**
     * 上传申请的资质信息
     * @param file
     * @param authToken
     * @return
     */
    @RequestMapping("upload-qualification")
    @DataCheckAnotation
    public String uploadQualification(MultipartFile file,String authToken){
        String msg = "fail";
        if(userService.uploadQualification(file,authToken)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 列出所有申请成为卖家的用户
     * @return
     */
    @RequestMapping("/seller-request")
    @DataCheckAnotation
    public List<UserInfo> dataCheck(String authToken){
        return userService.findSellerRequestList();
    }

    /**
     * 修改普通用户为卖家
     * @param authToken
     * @return
     * @throws ParseException
     */
    @RequestMapping("/passed")
    @DataCheckAnotation
    public String updateType(String account,String authToken){
        String msg = "fail";
        if(userService.updateType(account)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 根据authtoken获取用户信息
     * @param authToken
     * @return
     */
    @RequestMapping("/info")
    @DataCheckAnotation
    public UserInfo getUserInfoByAuthToken(String authToken){
        return userService.findUserInfoByAuthToken(authToken);
    }

    @RequestMapping("/test")
    @DataCheckAnotation
    public Map<String,Object> test(String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","success");
        map.put("token","token");
        return map;
    }

//    @RequestMapping("/login")
//    public String login(HttpServletRequest request, HttpServletResponse response) {
//        boolean tag = userService.findUserById(request.getParameter("id"));
//
//        if (tag) {
//            return "{\"ok\":\"ok\"}";
//        }else{
//            return "{\"false\":\"false\"}";
//        }
//    }

}
