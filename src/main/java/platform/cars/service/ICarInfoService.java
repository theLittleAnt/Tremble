package platform.cars.service;


import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;

import java.util.List;
import java.util.Map;

public interface ICarInfoService {
    Map<String,Object> listCarInfoByPage(int page, int size);

    CarInfo getCarInfoByCarId(String carId);

    int decreaseNumOfCar(String carId);

    boolean buy(Bill bill, String authToken);

    boolean dropCarInfo(String carId);

    boolean saveCarInfo(CarInfo carInfo);

    boolean updateCarInfo(CarInfo carInfo);

    Map<String,Object> findPaginatedCarInfoByOwner(int page,int size,String carOwner);
}
