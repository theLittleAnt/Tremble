package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.anotation.DataCheckAnotation;
import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;
import platform.cars.domain.User;
import platform.cars.service.ICarInfoService;
import platform.cars.service.IUserService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("car-info")
@CrossOrigin
public class CarInfoController {
    @Autowired
    private ICarInfoService carInfoService;

    @Autowired
    private IUserService userService;

    /**
     * 根据页数和条数获取车辆信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/paginated")
    public Map<String,Object> paginatedCarInfo( Integer page, Integer size){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        map.put("data",carInfoService.listCarInfoByPage(page,size));
        return map;
    }

    /**
     * 根据车辆id获取单条车辆信息
     * @param carId
     * @return
     */
    @RequestMapping("/single")
    public Map<String,Object> getCarInfoByCarId(String carId){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        map.put("data",carInfoService.getCarInfoByCarId(carId));
        return map;
    }

    /**
     * 购买车辆
     * @param bill
     * @param authToken
     * @return
     */
    @RequestMapping("buy")
    @DataCheckAnotation
    public Map<String,Object> buyCar(Bill bill,String authToken){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(carInfoService.buy(bill,authToken)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 删除车辆信息
     * @param carId
     * @param authToken
     * @return
     */
    @RequestMapping("drop")
    @DataCheckAnotation
    public Map<String,Object> dropCar(String carId, String authToken){
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(carInfoService.dropCarInfo(carId)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 保存车辆信息
     * @param file
     * @param carInfo
     * @param authToken
     * @return
     */
    @RequestMapping("/save")
    @DataCheckAnotation
    public Map<String,Object> saveCar(MultipartFile file,CarInfo carInfo,String authToken) throws Exception {
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(carInfoService.saveCarInfo(file,carInfo,authToken)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 修改车辆信息
     * @return
     */
    @RequestMapping("/alter")
    @DataCheckAnotation
    public Map<String,Object> updateCar(MultipartFile file,CarInfo carInfo,String authToken) throws Exception {
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        if(carInfoService.updateCarInfo(file,carInfo)){
            code = 200;
            msg = "success";
        }
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取卖家出售的所有车辆信息
     * @param page
     * @param size
     * @param authToken
     * @return
     * @throws ParseException
     */
    @RequestMapping("/owners")
    @DataCheckAnotation
    public Map<String,Object> paginatedCarInfoByOwner(Integer page, Integer size, String authToken){
        Map<String,Object> data = null;
        Map<String,Object> map = new HashMap<>();
        int code = 400;
        String msg = "fail";
        User user = userService.findUserByToken(authToken);
        if(null!=user) {
            code = 200;
            msg = "success";
            data = carInfoService.findPaginatedCarInfoByOwner(page, size, user.getAccount());
        }
        map.put("data",data);
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

}
