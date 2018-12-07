package platform.cars.domain;

import platform.cars.base.BaseObject;


public class CarInfo extends BaseObject {
    private String carId;
    private String carName;
    private float carPrice;
    private String carMainPic;
    private String carDescription;
    private String carOwner;
    private String carTradePlace;
    private int carNum;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String cardId) {
        this.carId = cardId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(float carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarMainPic() {
        return carMainPic;
    }

    public void setCarMainPic(String carMainPic) {
        this.carMainPic = carMainPic;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getCarTradePlace() {
        return carTradePlace;
    }

    public void setCarTradePlace(String carTradePlace) {
        this.carTradePlace = carTradePlace;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }
}
