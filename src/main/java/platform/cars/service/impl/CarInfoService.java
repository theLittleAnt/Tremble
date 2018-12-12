package platform.cars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import platform.cars.dao.IBillDao;
import platform.cars.dao.ICarInfoDao;
import platform.cars.dao.IUserDao;
import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;
import platform.cars.domain.User;
import platform.cars.service.ICarInfoService;
import platform.cars.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarInfoService implements ICarInfoService {

    @Autowired
    private ICarInfoDao carInfoDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBillDao billDao;

    @Autowired
    private CommonUtils commonUtils;
    /**
     * 根据传入的起始位置和大小返回车辆信息
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String,Object> listCarInfoByPage(int page, int size) {
        Map<String,Object> cars = new HashMap<>();
        cars.put("totalSize",carInfoDao.findAllCarInfo().size());
        page = page<=0?1:page;
        size = size<=0?10:size;
        cars.put("carsData",carInfoDao.listCarInfoByPage((page-1)*size,size));
        return cars;
    }

    /**
     * 根据车辆的id获取车辆的信息
     * @param carId
     * @return
     */
    @Override
    public CarInfo getCarInfoByCarId(String carId) {
         return carInfoDao.findCarInfoByCardId(carId);
    }

    /**
     * 购买时根据被买的车辆id减少车辆的数量
     * @param carId
     * @return
     */
    @Override
    @Transactional
    public int decreaseNumOfCar(String carId) {
        return carInfoDao.decreaseNumOfCar(carId);
    }

    /**
     * 根据token获取买家账号
     * 根据传入的订单车辆id减少车辆数
     * 生成订单id
     * 存入bill表
     * @param bill
     * @param authToken
     * @return
     */
    @Override
    @Transactional
    public boolean buy(Bill bill,String authToken) {
        boolean result = false;
        if(null!=bill && !StringUtils.isEmpty(bill.getCarId())){
            User buyer= userDao.findUserByToken(authToken);
            if(null!=buyer){
                carInfoDao.decreaseNumOfCar(bill.getCarId());
                bill.setBuyerAccount(buyer.getAccount());
                bill.setBillId(commonUtils.genAuthToken());
                if(billDao.saveBillInfo(bill)>0){
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 删除车辆信息
     * @param carId
     * @return
     */
    @Override
    @Transactional
    public boolean dropCarInfo(String carId) {
        boolean result = false;
        if(!StringUtils.isEmpty(carId)){
            result = carInfoDao.dropCarInfo(carId)>0?true:false;
        }
        return result;
    }

    /**
     * 保存车辆信息
     * @param carInfo
     * @return
     */
    @Override
    @Transactional
    public boolean saveCarInfo(CarInfo carInfo) {
        boolean result = false;
        if(null!=carInfo){
            carInfo.setCarId(commonUtils.genAuthToken());
            result = carInfoDao.saveCarInfo(carInfo)>0?true:false;
        }
        return result;
    }

    /**
     * 修改车辆信息
     * @param carInfo
     * @return
     */
    @Override
    @Transactional
    public boolean updateCarInfo(CarInfo carInfo) {
        boolean result = false;
        if(null!=carInfo){
            result = carInfoDao.updateCarInfo(carInfo)>0?true:false;
        }
        return result;
    }

    /**
     * 获取卖家下的所有车辆分页信息
     * @param carOwner
     * @return
     */
    @Override
    public Map<String,Object> findPaginatedCarInfoByOwner(int page,int size,String carOwner) {
        Map<String,Object> cars = null;
        if(!StringUtils.isEmpty(carOwner)){
            page = page<=0?1:page;
            size = size<=0?10:size;
            cars.put("carsData",carInfoDao.findPaginatedCarInfoByOwner(page,size,carOwner));
            cars.put("totalSize",carInfoDao.findAllCarInfoByOwner(carOwner).size());
        }
        return cars;
    }
}
