package platform.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
    @RequestMapping("/")
    public String login() {
        return "index";
    }

    @RequestMapping("/home")
    public String toHome() {
        return "jsp/public/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        return "index";
    }
}
