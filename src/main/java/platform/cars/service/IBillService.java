package platform.cars.service;


import platform.cars.domain.Bill;

import java.util.List;
import java.util.Map;

public interface IBillService {
    boolean saveBillInfo(Bill bill);

    Bill findOneBill(String billId);

    Map<String,Object> findBuyerPaginatedBill(Integer page, Integer size ,String authToken);

    boolean alterBillStatus(Bill bill);

    List<Bill> findAllBillByBuyerAccount(String buyerAccount);

    Map<String,Object> findSellerPaginatedBill(Integer page, Integer size, String carOwner);

}
