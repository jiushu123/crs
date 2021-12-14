package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.User;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

    // 根据用户名查询用户对象
    User queryUserByUserName(String userName);

    // 查询所有的角色
    @MapKey("")
    public List<Map<String, Object>> queryAllRoles();




}