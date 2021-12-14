package com.yjxxt.crs.controller;

import com.yjxxt.crs.annotations.RequiredPermission;
import com.yjxxt.crs.base.BaseController;
import com.yjxxt.crs.base.ResultInfo;
import com.yjxxt.crs.bean.Role;
import com.yjxxt.crs.query.RoleQuery;
import com.yjxxt.crs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;

    /*** 查询角色列表
     * * @return */
    @RequiredPermission(code = "4042")
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }

    /**
     * 跳转角色管理页面中转站
     * @return
     */
    @RequiredPermission(code = "40")
    @RequestMapping("role")
    public String index(){
        return "role/role";
    }

    @RequiredPermission(code = "4042")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> userList(RoleQuery roleQuery){
        return roleService.queryByParamsForTable(roleQuery);
    }

    @RequestMapping("addOrUpdateUserPage")
    public String addUserPage(Integer id, Model model){
        if(null != id){
            model.addAttribute("role",roleService.selectByPrimaryKey(id));
        }
        return "role/add_update";
    }

    @RequiredPermission(code = "4041")
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveRole(Role role){
        roleService.saveRole(role);
        return success("角色记录添加成功");
    }

    @RequiredPermission(code = "4043")
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Role role){
        roleService.updateRole(role);
        return success("角色记录更新成功");
    }

    @RequiredPermission(code = "4044")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer id){
        roleService.deleteRole(id);
        return success("角色记录删除成功");
    }

    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId, Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }

    /**
     * 角色授权添加
     * @param mids 所选中的资源id
     * @param roleId 当前角色id
     * @return
     */
    @RequiredPermission(code = "4045")
    @RequestMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer[] mids,Integer roleId){
        roleService.addGrant(mids,roleId);
        return success("权限添加成功");
    }
}
