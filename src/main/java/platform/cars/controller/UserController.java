package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import platform.cars.domain.User;
import platform.cars.domain.UserInfo;
import platform.cars.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpSession session) {
        String msg = "fail";
        User result = userService.checkIn(user);
        if(null!=result){
            session.setAttribute("userInfo",userService.findUserInfoByAccount(result.getAccount()));
            msg = "success";
        }
        return msg;
    }

    @RequestMapping("/gain-userinfo")
    public UserInfo test(String account) {
        return userService.findUserInfoByAccount(account);
    }

    @RequestMapping("/sellers")
    public List<UserInfo> getSellerList() {
        return userService.findSellerList();
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
