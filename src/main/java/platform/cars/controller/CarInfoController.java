package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public Map<String,Object> paginatedCarInfo(int page, int size){
        return carInfoService.listCarInfoByPage(page,size);
    }

    /**
     * 根据车辆id获取单条车辆信息
     * @param carId
     * @return
     */
    @RequestMapping("/single")
    public CarInfo getCarInfoByCarId(String carId){
        return carInfoService.getCarInfoByCarId(carId);
    }

    /**
     * 购买车辆
     * @param bill
     * @param authToken
     * @return
     */
    @RequestMapping("buy")
    @DataCheckAnotation
    public String buyCar(Bill bill,String authToken){
        String msg = "fail";
        if(carInfoService.buy(bill,authToken)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 删除车辆信息
     * @param carId
     * @param authToken
     * @return
     */
    @RequestMapping("drop")
    @DataCheckAnotation
    public String dropCar(String carId, String authToken){
        String msg = "fail";
        if(carInfoService.dropCarInfo(carId)){
            msg = "success";
        }
        return msg;
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
    public String saveCar(MultipartFile file,CarInfo carInfo,String authToken){
        String msg = "fail";
        if(carInfoService.saveCarInfo(file,carInfo)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 修改车辆信息
     * @return
     */
    @RequestMapping("/update")
    @DataCheckAnotation
    public String updateCar(MultipartFile file,CarInfo carInfo,String authToken){
        String msg = "fail";
        if(carInfoService.updateCarInfo(file,carInfo)){
            msg = "success";
        }
        return msg;
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
    public Map<String,Object> paginatedCarInfoByOwner(int page,int size,String authToken){
        Map<String,Object> bills = null;
        User user = userService.findUserByToken(authToken);
        if(null!=user) {
            bills = carInfoService.findPaginatedCarInfoByOwner(page, size, user.getAccount());
        }
        return bills;
    }

}
