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

    //主页
    @RequestMapping("/home")
    public String toHome() {
        return "jsp/public/home";
    }

    //个人中心
    @RequestMapping("/personal")
    public String toPersonal() {
        return "jsp/personalcenter/personal_center_info";
    }

    //个人中心用户订单
    @RequestMapping("/personal-user-bill")
    public String toPersonalUserBill() {
        return "jsp/personalcenter/personal_center_user_bill";
    }

    //个人中心卖家订单
    @RequestMapping("/personal-seller-bill")
    public String toPersonalSellerBill() {
        return "jsp/personalcenter/personal_center_seller_bill";
    }

    //个人中心卖家车辆信息
    @RequestMapping("/personal-seller-cars")
    public String toPersonalSellerCars() {
        return "jsp/personalcenter/personal_center_cars_info";
    }

    //个人中心用户申请成为卖家
    @RequestMapping("/personal-user-apply")
    public String toPersonalUserApply() {
        return "jsp/personalcenter/personal_center_user_apply";
    }

    //个人中心管理员审核
    @RequestMapping("/personal-admin-check")
    public String toPersonalAdminCheck() {
        return "jsp/personalcenter/personal_center_admin_check";
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
