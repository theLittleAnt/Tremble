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
     * 保存订单信息
     * @param bill
     * @return
     */
    @Override
    @Transactional
    public boolean saveBillInfo(Bill bill) {
        boolean result = false;
        if(null!=bill){
            bill.setBuyerAccount(commonUtils.genAuthToken());
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
    public Map<String,Object> findBuyerPaginatedBill(int page, int size , String authToken) {
        Map<String,Object> bills = new HashMap<>();
        User user = userDao.findUserByToken(authToken);
        if (null!=user){
            bills.put("totalSize",billDao.findAllBillByBuyerAccount(user.getAccount()).size());
        }
        page = page<=0?1:page;
        size = size<=0?10:size;
        bills.put("billsData",billDao.findBuyerPaginatedBill((page-1)*size,size,user.getAccount()));
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
        if(null!=bill){
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
    public Map<String, Object> findSallerPaginatedBill(int page, int size, String authToken) {
        Map<String,Object> bills = new HashMap<>();
        User user = userDao.findUserByToken(authToken);
        if (null!=user){
            bills.put("totalSize",billDao.findAllBillBySallerAccount(user.getAccount()).size());
        }
        page = page<=0?1:page;
        size = size<=0?10:size;
        bills.put("billsData",billDao.findSallerPaginatedBill((page-1)*size,size,user.getAccount()));
        return bills;
    }
}
