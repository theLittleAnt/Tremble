package platform.cars.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@CrossOrigin
public class MyExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public Map<String,Object> allExceptionHandler(Exception exception) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("msg","error occur");
        map.put("code",500);
        map.put("error",exception.getMessage());
        exception.printStackTrace();
        return map;
    }

}
