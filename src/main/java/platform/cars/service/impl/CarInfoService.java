package platform.cars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import platform.cars.dao.IBillDao;
import platform.cars.dao.ICarInfoDao;
import platform.cars.dao.IUserDao;
import platform.cars.domain.Bill;
import platform.cars.domain.CarInfo;
import platform.cars.domain.User;
import platform.cars.service.ICarInfoService;
import platform.cars.utils.CommonUtils;
import platform.cars.utils.FileUtils;

import java.io.IOException;
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

    @Autowired
    private FileUtils fileUtils;

    @Value("${web.upload-path}")
    private String path;
    /**
     * 根据传入的起始位置和大小返回车辆信息
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String,Object> listCarInfoByPage(Integer page, Integer size) {
        Map<String,Object> cars = new HashMap<>();
        cars.put("totalSize",carInfoDao.findAllCarInfo().size());
        page=commonUtils.checkInteger(page,1);
        size=commonUtils.checkInteger(size,12);
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
        if(!StringUtils.isEmpty(bill.getCarId())){
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
     * 包括删除车辆图片
     * @param carId
     * @return
     */
    @Override
    @Transactional
    public boolean dropCarInfo(String carId) {
        boolean result = false;
        if(!StringUtils.isEmpty(carId)){
            CarInfo carInfo = carInfoDao.findCarInfoByCardId(carId);
            if(null!=carInfo){
                String picPath = carInfo.getCarMainPic();
                if(!StringUtils.isEmpty(picPath)){
                    String fileName = carInfo.getCarMainPic();
                    fileName=fileName.substring(fileName.lastIndexOf("/"));
                    fileUtils.dropFile(path+fileName);
                }
                result = carInfoDao.dropCarInfo(carId)>0?true:false;
            }else{
                result = true;
            }
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
    public boolean saveCarInfo(MultipartFile file, CarInfo carInfo,String authToken) throws Exception {
        boolean result = false;
        if(!StringUtils.isEmpty(carInfo.getCarName())){
            String carId = commonUtils.genAuthToken();
            String fileName = "touxiang.jpg";//设置默认图片
            if(null!=file){
                String filePath = path;
                fileName = fileUtils.picFileName(file,carId);
                fileUtils.uploadFile(file.getBytes(),filePath,fileName);
            }
            User user = userDao.findUserByToken(authToken);
            if(null!=user){
                carInfo.setCarOwner(user.getAccount());
                carInfo.setCarId(carId);
                carInfo.setCarMainPic("/cars-sale/static/"+fileName);
                result = carInfoDao.saveCarInfo(carInfo)>0?true:false;
            }
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
    public boolean updateCarInfo(MultipartFile file,CarInfo carInfo) throws Exception {
        boolean result = false;
        if(!StringUtils.isEmpty(carInfo.getCarId())){
            if(null!=file){
                String filePath = path;
                String fileName = fileUtils.picFileName(file,carInfo.getCarId());
                if(fileName!=null){
                    fileUtils.uploadFile(file.getBytes(),filePath,fileName);
                    carInfo.setCarMainPic("/cars-sale/static/"+fileName);
                }
            }
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
    public Map<String,Object> findPaginatedCarInfoByOwner(Integer page,Integer size,String carOwner) {
        Map<String,Object> cars = new HashMap<>();
        if(!StringUtils.isEmpty(carOwner)){
            page=commonUtils.checkInteger(page,1);
            size=commonUtils.checkInteger(size,12);
            cars.put("carsData",carInfoDao.findPaginatedCarInfoByOwner((page-1)*size,size,carOwner));
            cars.put("totalSize",carInfoDao.findAllCarInfoByOwner(carOwner).size());
        }
        return cars;
    }
}
