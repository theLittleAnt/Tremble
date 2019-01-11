package platform.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

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

    @RequestMapping("/personal")
    public String toPersonal() {
        return "jsp/personalcenter/personal_center_info";
    }

    @RequestMapping("/personal-user-bill")
    public String toPersonalUserBill() {
        return "jsp/personalcenter/personal_center_user_bill";
    }


    /**
     * 删除cookie并且跳转到登陆页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (null!=cookies) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("authToken")){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "index";
    }

    @RequestMapping("/file")
    public String file(){
        return "upload";
    }

    @RequestMapping("/cookie")
    public void cookie(HttpServletResponse response){
        Cookie cookie = new Cookie("authToken","test");
        cookie.setMaxAge(30 * 60);//半小时过期
        cookie.setPath("/");//("/")表示的是访问当前工程下的所有webApp都会产生cookie
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
    }
}
