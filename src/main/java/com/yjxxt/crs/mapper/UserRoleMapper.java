package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {


    public int countUserRoleByUserId(Integer userId);

    public int deleteUserRoleByUserId(Integer userId);

    int insertRoleById(UserRole userRole);

}