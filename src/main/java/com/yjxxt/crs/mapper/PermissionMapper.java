package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    int deletePermissionsByModuleId(Integer mid);

    int countPermissionsByModuleId(Integer mid);

    List<Integer> queryRoleHasAllModuleIdsByRoleId(Integer roleId);

    //根据角色id查询拥有的资源数量
    Integer countPermissionByRoleId(Integer roleId);

    //根据角色id删除数据
    Integer deletePermissionsByRoleId(Integer roleId);

    //根据用户id查询资源权限码
    List<String> queryUserHasRolesHasPermissions(Integer userId);
}