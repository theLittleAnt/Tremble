package platform.cars.dao;


import platform.cars.domain.Bill;

import java.util.List;

public interface IBillDao {
    int saveBillInfo(Bill bill);

    Bill findOneBill(String billId);

    List<Bill> findPaginatedBill(int start,int size);

    int alterBillStatus(Bill bill);

    List<Bill> findAllBillByBuyerAccount(String buyerAccount);
}
