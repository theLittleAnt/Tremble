package platform.cars.service;


import platform.cars.domain.CarInfo;

import java.util.List;

public interface ICarInfoService {
    List<CarInfo> listCarInfoByPage(int page, int size);
}
