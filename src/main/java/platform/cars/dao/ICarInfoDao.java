package platform.cars.dao;


import platform.cars.domain.CarInfo;

import java.util.List;

public interface ICarInfoDao {

    int saveCarInfo(CarInfo carInfo);

    int dropCarInfo(String carId);

    int decreaseNumOfCar(String carId);

    int updateCarInfo(CarInfo carInfo);

    List<CarInfo> listCarInfoByPage(int start, int size);

    CarInfo findCarInfoByCardId(String carId);

    List<CarInfo> findAllCarInfo();

    List<CarInfo> findAllCarInfoByOwner(String carOwner);

    List<CarInfo> findPaginatedCarInfoByOwner(int start, int size, String carOwner);
}
