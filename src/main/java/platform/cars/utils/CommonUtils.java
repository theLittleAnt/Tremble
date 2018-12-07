package platform.cars.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class CommonUtils {

    private static CommonUtils instance;

    private CommonUtils(){}

    /**
     * 返回CommonUtils实例
     * @return
     */
    public static CommonUtils getInstance(){
        if(instance==null){
            instance = new CommonUtils();
        }
        return instance;
    }

    /**
     * 用UUID作为token
     * @return
     */
    public static String genAuthToken(){
        String token = new String(UUID.randomUUID().toString().replaceAll("-", ""));
        if(token == null || !StringUtils.isEmpty(token)){
            token=genAuthToken();
        }
        return token;
    }

}
