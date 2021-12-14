package com.yjxxt.crs.query;

import com.yjxxt.crs.base.BaseQuery;

public class ManageCarQuery extends BaseQuery {
    private String carName; // 车名
    private String carPrice; // 车辆价格
    private String carState; // 租赁状态

    public ManageCarQuery() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    @Override
    public String toString() {
        return "CarQuery{" +
                "carName='" + carName + '\'' +
                ", carPrice='" + carPrice + '\'' +
                ", carState='" + carState + '\'' +
                '}';
    }
}