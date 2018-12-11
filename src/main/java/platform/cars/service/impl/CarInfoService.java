package platform.cars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public boolean buy(Bill bill,String authToken) {
        boolean result = false;
        try {
            if(null!=bill.getCarId()){
                User buyer= userDao.findUserByToken(authToken);
                if(buyer!=null){
                    carInfoDao.decreaseNumOfCar(bill.getCarId());
                    bill.setBuyerAccount(buyer.getAccount());
                    bill.setBillId(commonUtils.genAuthToken());
                    if(billDao.saveBillInfo(bill)>0){
                        result = true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
