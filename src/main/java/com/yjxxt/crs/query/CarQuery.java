package com.yjxxt.crs.query;

import com.yjxxt.crs.base.BaseQuery;

public class CarQuery extends BaseQuery {
    private String carName; // 车名
    private String carOrder; // 车牌号

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(String carOrder) {
        this.carOrder = carOrder;
    }

    @Override
    public String toString() {
        return "CarQuery{" +
                "carName='" + carName + '\'' +
                ", carOrder='" + carOrder + '\'' +
                "} " + super.toString();
    }
}
