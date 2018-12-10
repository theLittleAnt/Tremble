package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;
import platform.cars.utils.CommonUtils;

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
        if(userService.register(user)>0){
            msg = "success";
        }
        return msg;
    }

//    @RequestMapping("/gain-userinfo")
    public UserInfo test(String account) {
        return userService.findUserInfoByAccount(account);
    }

    /**
     * 获取卖家信息列表
     * @param token
     * @return
     */
    @RequestMapping("/sellers")
    public List<UserInfo> getSellerList(String token) {
        return userService.findSellerList();
    }

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    @RequestMapping("alter-pwd")
    public String alterPwd(User user){
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
    public String alterUserInfo(UserInfo userInfo,String authToken){
        String msg = "fail";
        if(userService.updateUserInfo(userInfo,authToken)){
            msg="success";
        }
        return msg;
    }



    @RequestMapping("/test")
    public Map<String,Object> test(){
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
