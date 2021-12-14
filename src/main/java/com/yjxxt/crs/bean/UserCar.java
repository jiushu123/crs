package com.yjxxt.crs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserCar {
    private Integer id;

    private Integer userId;

    private Integer carId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date rentDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}