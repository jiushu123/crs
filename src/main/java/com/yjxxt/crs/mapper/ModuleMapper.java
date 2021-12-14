package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.Module;
import com.yjxxt.crs.dto.TreeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper extends BaseMapper<Module,Integer> {

    List<Module> queryModules();

    int countSubModuleByParentId(Integer mid);

    Module queryModuleByOptValue(String optValue);

    Module queryModuleByGradeAndModuleName(Integer grade, String moduleName);

    Module queryModuleByParent(Integer parentId);

    //查询所有资源模块信息
    List<TreeDto> selectModules();
}