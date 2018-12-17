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

        Object obj = null;

        if(StringUtils.isEmpty(authToken) || !userService.checkToken(authToken)){
            Map<String,Object> map = new HashMap<>();
            map.put("code",401);
            map.put("msg","token error");
            obj = map;
        }else{
            //joinPoint.proceed()获取的是方法的返回值,即目标方法执行后的结果。PS:目标方法会被执行
            obj = joinPoint.proceed();
        }
        return obj;
    }
}
