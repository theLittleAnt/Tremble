package platform.cars.dao;


import platform.cars.domain.Bill;

import java.util.List;
import java.util.Map;

public interface IBillDao {
    int saveBillInfo(Bill bill);

    int dropBillByBillId(String billId);

    int alterBillStatus(Bill bill);

    Bill findOneBill(String billId);

    Map<String,Object> findBuyerPaginatedBill(int start, int size, String buyerAccount);

    List<Bill> findAllBillByBuyerAccount(String buyerAccount);

    Map<String,Object> findSallerPaginatedBill(int start, int size, String carOwner);

    List<Bill> findAllBillBySallerAccount(String carOwner);
}
