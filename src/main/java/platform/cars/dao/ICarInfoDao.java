package platform.cars.dao;


import platform.cars.domain.CarInfo;

import java.util.List;

public interface ICarInfoDao {
    List<CarInfo> listCarInfoByPage(int start, int size);

    CarInfo findCarInfoByCardId(String carId);

    int decreaseNumOfCar(String carId);
}
