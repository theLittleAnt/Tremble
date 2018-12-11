package platform.cars.service;


import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;

import java.util.Map;

public interface ICarInfoService {
    Map<String,Object> listCarInfoByPage(int page, int size);

    CarInfo getCarInfoByCarId(String carId);

    int decreaseNumOfCar(String carId);

    boolean buy(Bill bill, String authToken);
}
