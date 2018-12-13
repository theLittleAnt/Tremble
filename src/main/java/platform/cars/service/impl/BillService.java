package platform.cars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import platform.cars.dao.IBillDao;
import platform.cars.dao.IUserDao;
import platform.cars.domain.Bill;
import platform.cars.domain.User;
import platform.cars.service.IBillService;
import platform.cars.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillDao billDao;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private IUserDao userDao;

    /**
     * @Transactional 用于事务管理，对于增删改加事务，查放开
     * 保存订单信息
     * @param bill
     * @return
     */
    @Override
    @Transactional
    public boolean saveBillInfo(Bill bill) {
        boolean result = false;
        if(!StringUtils.isEmpty(bill.getBuyerAccount()) && !StringUtils.isEmpty(bill.getCarId())){
            bill.setBillId(commonUtils.genAuthToken());
            result = billDao.saveBillInfo(bill)>0?true:false;
        }
        return result;
    }

    /**
     * 根据billId获取订单信息
     * @param billId
     * @return
     */
    @Override
    public Bill findOneBill(String billId) {
        Bill bill = null;
        if(!StringUtils.isEmpty(billId)){
            bill = billDao.findOneBill(billId);
        }
        return bill;
    }

    /**
     * 判断传入的页数与条数是否为合适值，返回分页后的内容
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String,Object> findBuyerPaginatedBill(Integer page,Integer size,String authToken) {
        Map<String,Object> bills = new HashMap<>();
        User user = userDao.findUserByToken(authToken);
        if (null!=user){
            bills.put("totalSize",billDao.findAllBillByBuyerAccount(user.getAccount()).size());
            page=commonUtils.checkInteger(page,1);
            size=commonUtils.checkInteger(size,10);
            bills.put("billsData",billDao.findBuyerPaginatedBill((page-1)*size,size,user.getAccount()));
        }
        return bills;
    }

    /**
     * 修改订单的信息
     * @param bill
     * @return
     */
    @Override
    @Transactional
    public boolean alterBillStatus(Bill bill) {
        boolean result = false;
        if(!StringUtils.isEmpty(bill.getBuyerAccount())){
            result = billDao.alterBillStatus(bill)>0?true:false;
        }
        return result;
    }

    /**
     * 根据买家id返回所有的订单
     * @return
     */
    @Override
    public List<Bill> findAllBillByBuyerAccount(String buyerAccount) {
        return billDao.findAllBillByBuyerAccount(buyerAccount);
    }

    /**
     * 返回卖家收到的订单信息
     * @param page
     * @param size
     * @param authToken
     * @return
     */
    @Override
    public Map<String, Object> findSellerPaginatedBill(Integer page, Integer size, String authToken) {
        Map<String,Object> bills = new HashMap<>();
        User user = userDao.findUserByToken(authToken);
        if (null!=user){
            bills.put("totalSize",billDao.findAllBillBySallerAccount(user.getAccount()).size());
            page=commonUtils.checkInteger(page,1);
            size=commonUtils.checkInteger(size,10);
            bills.put("billsData",billDao.findSallerPaginatedBill((page-1)*size,size,user.getAccount()));
        }
        return bills;
    }
}
