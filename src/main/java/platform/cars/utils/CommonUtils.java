package platform.cars.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class CommonUtils {

    /**
     * 用UUID作为token
     * @return
     */
    public String genAuthToken(){
        String token = new String(UUID.randomUUID().toString().replaceAll("-", ""));
        if(token == null || StringUtils.isEmpty(token)){
            token=genAuthToken();
        }
        return token;
    }

}
