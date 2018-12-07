package platform.cars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.cars.dao.ICarInfoDao;
import platform.cars.domain.CarInfo;
import platform.cars.service.ICarInfoService;

import java.util.List;

@Service
public class CarInfoService implements ICarInfoService {

    @Autowired
    ICarInfoDao carInfoDao;

    /**
     * 根据传入的起始位置和大小返回车辆信息
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<CarInfo> listCarInfoByPage(int page, int size) {
        return carInfoDao.listCarInfoByPage((page-1)*size,size);
    }
}
