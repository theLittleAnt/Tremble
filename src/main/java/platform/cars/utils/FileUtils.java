package platform.cars.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class FileUtils {

    public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public String picFilePath(){
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String path = request.getSession().getServletContext().getRealPath("/");
        path=path.replace("webapp\\","resources\\static\\pictures\\");
        return path;
    }

    public String picFileName(MultipartFile file,String name){
        String fileName = null;
        if(file!=null){
            String contentType = file.getContentType();
            if("image/jpeg".equals(contentType)){
                fileName = file.getOriginalFilename();
                fileName = fileName.substring(fileName.indexOf("."));
                fileName = name+fileName;
            }
        }
        return fileName;
    }

    public boolean dropFile(String filePath){
        boolean result = false;
        if(!StringUtils.isEmpty(filePath)){
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
            result = true;
        }
        return result;
    }
}
