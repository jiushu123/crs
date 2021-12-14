package com.yjxxt.crs.service;

import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.bean.UserRole;
import com.yjxxt.crs.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole,Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;

}
