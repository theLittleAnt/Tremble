package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
        int code = 400;
        String msg = "fail";
        User checkedUser = userService.checkIn(user);
        if(null!=checkedUser){
            code = 200;
            msg = "success";
        }
        map.put("msg",msg);
        map.put("code",code);
        map.put("data",checkedUser);
        return map;
    }

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(userService.register(user)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取卖家信息列表
     * @param authToken
     * @return
     */
    @RequestMapping("/sellers")
    @DataCheckAnotation
    public Map<String,Object> getSellerList(String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        map.put("data",userService.findSellerList());
        return map;
    }

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    @RequestMapping("alter-pwd")
    @DataCheckAnotation
    public Map<String,Object> alterPwd(User user,String authToken){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(userService.updatePwd(user)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("alter-userinfo")
    @DataCheckAnotation
    public Map<String,Object> alterUserInfo(UserInfo userInfo,String authToken){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(userService.updateUserInfo(userInfo,authToken)){
            code=200;
            msg="success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 上传申请的资质信息
     * @param file
     * @param authToken
     * @return
     */
    @RequestMapping("upload-qualification")
    @DataCheckAnotation
    public Map<String,Object> uploadQualification(MultipartFile file,String authToken){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(userService.uploadQualification(file,authToken)){
            code=200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 列出所有申请成为卖家的用户
     * @return
     */
    @RequestMapping("/seller-request")
    @DataCheckAnotation
    public Map<String,Object> dataCheck(String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("data",userService.findSellerRequestList());
        map.put("code",200);
        map.put("msg","success");
        return map;
    }

    /**
     * 修改普通用户为卖家
     * @param authToken
     * @return
     * @throws ParseException
     */
    @RequestMapping("/passed")
    @DataCheckAnotation
    public Map<String,Object> updateType(String account,String authToken){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(userService.updateType(account)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 根据authtoken获取用户信息
     * @param authToken
     * @return
     */
    @RequestMapping("/info")
    @DataCheckAnotation
    public Map<String,Object> getUserInfoByAuthToken(String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("data",userService.findUserInfoByAuthToken(authToken));
        map.put("code",200);
        map.put("msg","success");
        return map;
    }

    @RequestMapping("/test")
    @DataCheckAnotation
    public Map<String,Object> test(String authToken){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","success");
        map.put("token","token");
        return map;
    }

    @RequestMapping("/extest")
    public Map<String,Object> test() throws Exception{
        Map<String,Object> map = new HashMap<>();

        int a = 5/0;
        map.put("msg","success");
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
