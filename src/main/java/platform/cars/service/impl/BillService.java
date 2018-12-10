package platform.cars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.cars.dao.IBillDao;
import platform.cars.domain.Bill;
import platform.cars.service.IBillService;
import platform.cars.utils.CommonUtils;

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillDao billDao;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public boolean saveBillInfo(Bill bill) {
        boolean result = false;
        bill.setBuyerAccount(commonUtils.genAuthToken());
        if(billDao.saveBillInfo(bill)>0){
            result=true;
        }
        return result;
    }
}
