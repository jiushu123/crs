package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.Role;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper extends BaseMapper<Role,Integer> {


    // 查询角色列表
    @MapKey("")
    public List<Map<String,Object>> queryAllRoles(Integer userId);

    //根据角色名查询角色对象
    Role queryRoleByRoleName(String roleName);



}