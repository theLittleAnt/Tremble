package platform.cars.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import platform.cars.base.BaseObject;
import platform.cars.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class DataCheckAspect {

    @Autowired
    private IUserService userService;

    /**
     * 对注解有DataCheckAnotation的类与方法进行拦截
     */
//    @Pointcut("execution(public * platform.cars.controller.UserController.test(..))")
    @Pointcut("@annotation(platform.cars.anotation.DataCheckAnotation)")
    public void requestCheck(){}

    @Around("requestCheck()")
    public Object beforeRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String authToken = request.getParameter("authToken");

        //joinPoint.proceed()获取的是方法的返回值
        Object obj = joinPoint.proceed();

        if(StringUtils.isEmpty(authToken) || !userService.checkToken(authToken)){
            if(obj instanceof String){
                obj = "token error";
            }else if(obj instanceof Map){
                Map<String,Object> map = new HashMap<>();
                map.put("msg","token error");
                obj = map;
            }else if(obj instanceof List){
                obj = new ArrayList<>();
                ((List) obj).add("token error");
            }else if(obj instanceof BaseObject){
                obj = null;
            }
        }
        return obj;
    }
}
