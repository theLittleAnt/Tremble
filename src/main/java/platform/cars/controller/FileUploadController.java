package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.utils.FileUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private FileUtils fileUtils;

    @RequestMapping("/upload")
    public String upload(MultipartFile file, String text) throws Exception {
        String filePath = fileUtils.picFilePath();
        String fileName = fileUtils.picFileName(file,text);
        fileUtils.uploadFile(file.getBytes(), filePath, fileName);
        return "ok";
    }

    @RequestMapping("/rest-cookie")
    public void cookie(HttpServletResponse response){
        Cookie cookie = new Cookie("authToken","test1");
        cookie.setMaxAge(30 * 60);//半小时过期
        cookie.setPath("/");//("/")表示的是访问当前工程下的所有webApp都会产生cookie
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
    }
}
