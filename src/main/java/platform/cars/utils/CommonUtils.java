package platform.cars.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public Date strToDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            if(null!=dateStr)
                date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String dateToStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = null;
        if(null!=date)
            dateStr = sdf.format(date);
        return dateStr;
    }

}
