package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public String buyCar(Bill bill,String authToken) throws ParseException {
        String msg = "fail";
        if(userService.checkToken(authToken) && carInfoService.buy(bill,authToken)){
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
    public String dropCar(String carId,String authToken) throws ParseException {
        String msg = "fail";
        if(userService.checkToken(authToken)){
            carInfoService.dropCarInfo(carId);
            msg = "success";
        }
        return msg;
    }

    /**
     * 保存车辆信息
     * @return
     */
    @RequestMapping("/save")
    public String saveCar(CarInfo carInfo,String authToken) throws ParseException {
        String msg = "fail";
        if(userService.checkToken(authToken) && carInfoService.saveCarInfo(carInfo)){
            msg = "success";
        }
        return msg;
    }

    /**
     * 修改车辆信息
     * @return
     */
    @RequestMapping("/update")
    public String updateCar(CarInfo carInfo,String authToken) throws ParseException {
        String msg = "fail";
        if(userService.checkToken(authToken) && carInfoService.updateCarInfo(carInfo)){
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
    public Map<String,Object> paginatedCarInfoByOwner(int page,int size,String authToken) throws ParseException {
        Map<String,Object> bills = null;
        if(userService.checkToken(authToken)){
            User user = userService.findUserByToken(authToken);
            bills = carInfoService.findPaginatedCarInfoByOwner(page,size,user.getAccount());
        }
        return bills;
    }

}
