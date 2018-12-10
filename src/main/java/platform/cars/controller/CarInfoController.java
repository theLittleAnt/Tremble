package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;
import platform.cars.domain.User;
import platform.cars.service.IBillService;
import platform.cars.service.ICarInfoService;
import platform.cars.service.IUserService;
import platform.cars.utils.CommonUtils;

import java.util.List;

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
    public List<CarInfo> paginatedCarInfo(int page,int size){
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
    public String buyCar(Bill bill,String authToken){
        String msg = "fail";
        try{
            if(null!=bill && null!=authToken && userService.checkToken(authToken) && carInfoService.buy(bill,authToken)){
                msg = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
