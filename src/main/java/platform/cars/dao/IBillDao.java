package platform.cars.dao;


import platform.cars.domain.Bill;

public interface IBillDao {
    int saveBillInfo(Bill bill);
}
