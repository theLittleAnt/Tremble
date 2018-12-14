package platform.cars.service;


import org.springframework.web.multipart.MultipartFile;
import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ICarInfoService {
    Map<String,Object> listCarInfoByPage(Integer page, Integer size);

    CarInfo getCarInfoByCarId(String carId);

    int decreaseNumOfCar(String carId);

    boolean buy(Bill bill, String authToken);

    boolean dropCarInfo(String carId);

    boolean saveCarInfo(MultipartFile file,CarInfo carInfo,String authToken) throws Exception;

    boolean updateCarInfo(MultipartFile file,CarInfo carInfo) throws Exception;

    Map<String,Object> findPaginatedCarInfoByOwner(Integer page,Integer size,String carOwner);
}
