package com.yjxxt.crs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Car {
    private Integer id;

    private String carName;

    private String carOrder;

    private Double carPrice;

    private Integer carState;//

    private String description;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date assignTime;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date rentTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date returnTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(String carOrder) {
        this.carOrder = carOrder == null ? null : carOrder.trim();
    }

    public Integer getCarState() {
        return carState;
    }

    public void setCarState(Integer carState) {
        this.carState = carState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}