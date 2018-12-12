package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.utils.FileUtils;

@RestController
public class FileUploadController {

    @Autowired
    private FileUtils fileUtils;

    @RequestMapping("/upload")
    public String upload(MultipartFile file, String text){
        String filePath = fileUtils.picFilePath();
        String fileName = fileUtils.picFileName(file,text);
        try {
            fileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
