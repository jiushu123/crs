package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.UserCar;
import com.yjxxt.crs.query.CarQuery;
import com.yjxxt.crs.query.CarRentQuery;
import com.yjxxt.crs.query.ManageCarQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarMapper extends BaseMapper<Car,Integer> {



    List<Car> manageCarSelectByParams(ManageCarQuery query);

    int manageCarDeleteBatch(Integer[] ids);

    int changeCarState(@Param("ids") Integer[] ids, @Param("rentTime") Date rentTime);

    int insertUserCarByParams(UserCar userCar);

    int countUserCarByUserId(Integer userId);

    List<Car> RentCarSelectByParams(CarRentQuery rent);

    List<Car> selectByParamsAndId(CarQuery query, Integer userId);

    Integer deleteByCarId(Integer[] ids);
}