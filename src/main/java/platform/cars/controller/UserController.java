package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.annotation.DataCheckAnotation;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 根据登录的账号密码返回消息，
     * 带回token信息和用户类型
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Map<String, Object> login(User user, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        User checkedUser = userService.checkIn(user);
        if (null != checkedUser) {
            code = 200;
            msg = "success";
            Cookie authCookie = new Cookie("authToken", checkedUser.getAuthToken());
            authCookie.setMaxAge(30 * 60);//半小时过期
            authCookie.setPath("/");//("/")表示的是访问当前工程下的所有webApp都会产生cookie
            authCookie.setHttpOnly(true);//true表示JS不能操作cookie，false表示可以
            response.addCookie(authCookie);
            checkedUser.setAuthToken(null);
        }
        map.put("msg", msg);
        map.put("code", code);
        map.put("data", checkedUser);
        return map;
    }

    /**
     * 注册用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if (userService.register(user)) {
            code = 200;
            msg = "success";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 获取卖家信息列表
     *
     * @return
     */
    @RequestMapping("/sellers")
    @DataCheckAnotation
    public Map<String, Object> getSellerList() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "success");
        map.put("data", userService.findSellerList());
        return map;
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     */
    @RequestMapping("alter-pwd")
    @DataCheckAnotation
    public Map<String, Object> alterPwd(User user,@CookieValue("authToken") String authToken) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        user.setAuthToken(authToken);
        if (userService.updatePwd(user)) {
            code = 200;
            msg = "success";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @param authToken
     * @return
     */
    @RequestMapping("alter-userinfo")
    @DataCheckAnotation
    public Map<String, Object> alterUserInfo(UserInfo userInfo, @CookieValue("authToken") String authToken) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if (userService.updateUserInfo(userInfo, authToken)) {
            code = 200;
            msg = "success";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 上传申请的资质信息
     *
     * @param file
     * @param authToken
     * @return
     */
    @RequestMapping("upload-qualification")
    @DataCheckAnotation
    public Map<String, Object> uploadQualification(MultipartFile file, @CookieValue("authToken") String authToken) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if (userService.uploadQualification(file, authToken)) {
            code = 200;
            msg = "success";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 列出所有申请成为卖家的用户
     *
     * @return
     */
    @RequestMapping("/seller-request")
    @DataCheckAnotation
    public Map<String, Object> dataCheck() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.findSellerRequestList());
        map.put("code", 200);
        map.put("msg", "success");
        return map;
    }

    /**
     * 修改普通用户为卖家
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping("/passed")
    @DataCheckAnotation
    public Map<String, Object> updateType(String account) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if (userService.updateType(account)) {
            code = 200;
            msg = "success";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 撤销用户的申请
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping("/erase")
    @DataCheckAnotation
    public Map<String, Object> updateQualification(String account) {
        Map<String, Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if (userService.updateQualification(account)) {
            code = 200;
            msg = "success";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 根据authtoken获取用户信息
     *
     * @param authToken
     * @return
     */
    @RequestMapping("/info")
    @DataCheckAnotation
    public Map<String, Object> getUserInfoByAuthToken(@Nullable @CookieValue("authToken") String authToken) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.findUserInfoByAuthToken(authToken));
        map.put("code", 200);
        map.put("msg", "success");
        return map;
    }

    /**
     * 根据ID获取用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/user-info")
    public Map<String, Object> getUserInfoById(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.findUserInfoById(userId));
        map.put("code", 200);
        map.put("msg", "success");
        return map;
    }


    @RequestMapping("/test")
    @DataCheckAnotation
    public Map<String, Object> test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "success");
        map.put("token", "token");
        return map;
    }

    @RequestMapping("/extest")
    public Map<String, Object> test() throws Exception {
        Map<String, Object> map = new HashMap<>();

        int a = 5 / 0;
        map.put("msg", "success");
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
