package com.yjxxt.crs.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.bean.Module;
import com.yjxxt.crs.dto.TreeDto;
import com.yjxxt.crs.mapper.ModuleMapper;
import com.yjxxt.crs.mapper.PermissionMapper;
import com.yjxxt.crs.query.ModuleQuery;
import com.yjxxt.crs.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    //回显当前选中的角色拥有的所有权限
    public List<TreeDto> findModules(Integer roleId){
        //查询所有的资源
        List<TreeDto> treeDtos = moduleMapper.selectModules();

        //根据角色id查询资源id
        List<Integer> integer = permissionMapper.queryRoleHasAllModuleIdsByRoleId(roleId);

        if (null != integer && integer.size()>0){
            treeDtos.forEach(treeDto -> {
                if (integer.contains(treeDto.getId())){
                    treeDto.setChecked(true);
                }
            });
        }

        return treeDtos;
    }


    /*** 多条件分页查询用户数据 * @param query * @return */
    public Map<String, Object> moduleList(ModuleQuery query) {
        Map<String, Object> map = new HashMap();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<Module> pageInfo = new PageInfo(moduleMapper.selectByParams(query));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    //创建添加资源
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveModule(Module module) {
        //判断权限名是否存在
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "请输入权限名!");
        //获取权限等级
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "权限层级不合法!");

        if(grade == 0 ){
            Integer parentId = module.getParentId();
            AssertUtil.isTrue(-1 != parentId , "最高等级的权限不可以拥有父权限");
        }

        //权限码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "请输入权限码!");
        //权限码不可重复
        AssertUtil.isTrue(null != moduleMapper.queryModuleByOptValue(module.getOptValue()), "权限码重复!");
        //父级权限必须存在
        if (grade != 0) {
            Integer parentId = module.getParentId();
            AssertUtil.isTrue(null == parentId || null == selectByPrimaryKey(parentId), "请指定正确的上级权限!");
            //获取赋权限码
            String parentOptValue = moduleMapper.queryModuleByParent(module.getParentId()).getOptValue();
            //子权限码必须包含父权限码
            AssertUtil.isTrue(!module.getOptValue().contains(parentOptValue), "权限码设定不合法!");
        }

        //设置权限有效
        module.setIsValid((byte) 1);
        //创建日期修改日期
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());
        //创建权限
        AssertUtil.isTrue(insertSelective(module) < 1, "菜单添加失败!");
    }


    //修改资源
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) {
     //权限名不能为空
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "请指定权限名称!");
        //获取权限等级
        Integer grade = module.getGrade();
        //判断菜单等级是否合法
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
        //根据父id和资源名字查找资源
        Module temp = moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName());
        if (null != temp) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "该层级下菜单已存在!");
        }

        if (grade != 0) {
            Integer parentId = module.getParentId();
            AssertUtil.isTrue(null == parentId || null == selectByPrimaryKey(parentId), "请指定上级菜单!");
            //查询到父权限
            String parentOptValue = moduleMapper.queryModuleByParent(module.getParentId()).getOptValue();
            //子权限码必须包含父权限码
            AssertUtil.isTrue(!module.getOptValue().contains(parentOptValue), "权限码设定不合法!");
        }
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "请输入权限码!");
        //根据权限码查询该权限
        temp = moduleMapper.queryModuleByOptValue(module.getOptValue());
        if (null != temp) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "权限码已存在!");
        }
        module.setUpdateDate(new Date());
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module) < 1, "菜单更新失败!");
    }

    //根据id删除资源
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModuleById(Integer mid) {
        Module temp = selectByPrimaryKey(mid);
        AssertUtil.isTrue(null == mid || null == temp, "待删除记录不存在!");
        /*** 如果存在子菜单 不允许删除 */
        //根据父id查询所有的子资源数量
        int count = moduleMapper.countSubModuleByParentId(mid);
        AssertUtil.isTrue(count > 0, "存在子菜单，不支持删除操作!");
        // 权限表
        //查询中间表中所有的关于该资源的数量
        count = permissionMapper.countPermissionsByModuleId(mid);
        //如果存在资源被占用，则不允许删除
        AssertUtil.isTrue(count>0, "资源被占用，无法删除！！!");
        //设置为无效资源
        temp.setIsValid((byte) 0);
        //将此资源改为无效资源
        AssertUtil.isTrue(updateByPrimaryKeySelective(temp) < 1, "菜单删除失败!");
    }

}
