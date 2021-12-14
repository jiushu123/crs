package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.UserCar;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCarMapper extends BaseMapper<UserCar,Integer> {

    int deleteUserRoleByUserId(Integer userId);
}