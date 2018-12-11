package platform.cars.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import platform.cars.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DataCheckAspect {

    @Autowired
    private IUserService userService;

//    @Pointcut("execution(public * platform.cars.controller.UserController.test(..))")
    @Pointcut("@annotation(platform.cars.anotation.DataCheckAnotation)")
    public void requestCheck(){}

    @Around("requestCheck()")
    public Object beforeRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String authToken = request.getParameter("authToken");
        Map<String,Object> map = new HashMap<>();
        map.put("msg","fail");
        if(authToken==null || !userService.checkToken(authToken)){
            return map;
        }else {
            return joinPoint.proceed();
        }
    }
}
