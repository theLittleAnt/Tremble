package platform.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.cars.domain.CarInfo;
import platform.cars.service.ICarInfoService;

import java.util.List;

@RestController
@RequestMapping("car-info")
public class CarInfoController {
    @Autowired
    ICarInfoService carInfoService;

    @RequestMapping("/paginated")
    public List<CarInfo> paginatedCarInfo(int page,int size){
        return carInfoService.listCarInfoByPage(page,size);
    }
}
