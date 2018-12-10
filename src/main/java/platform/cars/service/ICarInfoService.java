package platform.cars.service;


import jdk.nashorn.internal.parser.Token;
import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;

import java.util.List;

public interface ICarInfoService {
    List<CarInfo> listCarInfoByPage(int page, int size);

    CarInfo getCarInfoByCarId(String carId);

    int decreaseNumOfCar(String carId);

    boolean buy(Bill bill, String authToken);
}
