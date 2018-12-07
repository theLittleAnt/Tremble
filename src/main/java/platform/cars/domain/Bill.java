package platform.cars.domain;

import platform.cars.base.BaseObject;


public class Bill extends BaseObject {
    private String billId;
    private int status;
    private String buyerAccount;
    private String sallerAccount;
    private String carId;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getSallerAccount() {
        return sallerAccount;
    }

    public void setSallerAccount(String sallerAccount) {
        this.sallerAccount = sallerAccount;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
