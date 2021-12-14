package com.yjxxt.crs.query;

import com.yjxxt.crs.base.BaseQuery;
import org.springframework.context.annotation.Configuration;


public class CarRentQuery extends BaseQuery {

    private String carName;//车辆名称
    private Double carPrice;//价格
    private String carState;//状态

    public CarRentQuery() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

}
