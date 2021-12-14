package com.yjxxt.crs.controller;

import com.yjxxt.crs.annotations.RequiredPermission;
import com.yjxxt.crs.base.BaseController;
import com.yjxxt.crs.bean.Module;
import com.yjxxt.crs.base.ResultInfo;;
import com.yjxxt.crs.dto.TreeDto;
import com.yjxxt.crs.query.ModuleQuery;
import com.yjxxt.crs.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;

    //跳转至资源页面
    @RequiredPermission(code = "50")
    @RequestMapping("module")
    public String index() {
        return "module/module";
    }

    //显示所有资源
    @RequiredPermission(code = "5052")
    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(Integer roleId){

        //System.out.println(roleId+"----------------as9d4f84sa56d464sd6f46sd4f64sd65f");

        return moduleService.findModules(roleId);
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> moduleList(ModuleQuery query) {

        return moduleService.moduleList(query);
    }



    // 添加资源页视图转发
    @RequiredPermission(code = "5051")
    @RequestMapping("addModulePage")
    public String addModulePage() {

        return "module/add";
    }


    // 更新资源页视图转发
    @RequiredPermission(code = "5053")
    @RequestMapping("updateModulePage")
    public String updateModulePage(Integer id, Model model) {
        model.addAttribute("module", moduleService.selectByPrimaryKey(id));
        return "module/update";
    }

    //添加资源
    @RequiredPermission(code = "5051")
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveModule(Module module) {
        moduleService.saveModule(module);
        return success("菜单添加成功");
    }

    //修改资源
    @RequiredPermission(code = "5053")
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateModule(Module module) {
        moduleService.updateModule(module);
        return success("菜单更新成功");
    }


    //删除资源
    @RequiredPermission(code = "5054")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteModule(Integer id) {
        System.out.println("-----------------------------------------------------"+id);
        moduleService.deleteModuleById(id);
        return success("菜单删除成功");
    }


}
