package com.yjxxt.crs.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.crs.base.BaseService;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.bean.UserCar;
import com.yjxxt.crs.mapper.CarMapper;
import com.yjxxt.crs.mapper.UserCarMapper;
import com.yjxxt.crs.mapper.UserMapper;
import com.yjxxt.crs.query.CarQuery;
import com.yjxxt.crs.query.CarRentQuery;
import com.yjxxt.crs.query.ManageCarQuery;
import com.yjxxt.crs.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService extends BaseService<Car,Integer> {

    @Resource
    private CarMapper carMapper;


    //------------------------------我的车辆---------------------------------------



    /**
     *     用户还车
     * @param ids
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance (Integer[] ids) {
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择车辆！");
        // 还车
        AssertUtil.isTrue(carMapper.deleteBatch(ids) < 0, "还车失败！");
        //删除历史租车记录 t_user_car
        AssertUtil.isTrue(carMapper.deleteByCarId(ids) < 0,"还车失败! ");
    }



    /**
     * 多条件分页查询营销机会 (BaseService 中有对应的方法)
     * @param query
     * @return
     */
    public Map<String, Object> querySaleChanceByParams (@Param("query") CarQuery query, @Param("userId") Integer userId) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        System.out.println(carMapper.selectByParamsAndId(query,userId)+"******************************************");
        PageInfo<Car> pageInfo = new PageInfo<>(carMapper.selectByParamsAndId(query,userId));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }




    //------------------------------车辆设置---------------------------------------
    /**
     * 分页查询
     * @param query
     * @return
     */
    public Map<String, Object> queryCarRentalByParams(ManageCarQuery query) {

        Map<String,Object> map = new HashMap<>();
        //分页处理
        PageHelper.startPage(query.getPage(),query.getLimit());
        //
        PageInfo<Car> pageInfo = new PageInfo(carMapper.manageCarSelectByParams(query));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        //返回
        return map;
    }

    /**
     * 车辆添加操作
     * @param car
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCar(Car car) {
        //1.参数校验
        checkParams(car.getCarName(),car.getCarPrice(),car.getCarOrder());
        //2.设置相关参数默认值
        //
        //设定默认值（添加车辆默认为：可租赁 1）
        car.setCarState(1);   //设置未可租赁
        car.setAssignTime(new Date());
        //是否添加成功
        AssertUtil.isTrue(insertSelective(car)<1,"数据添加失败！");
    }


    /**
     * 基本参数校验
     * @param carName
     * @param carPrice
     * @param carOrder
     */
    public void checkParams(String carName, Double carPrice, String carOrder){
        AssertUtil.isTrue(StringUtils.isBlank(carName),"请输入汽车名！");
        AssertUtil.isTrue(StringUtils.isBlank(carPrice.toString()),"请输入价格！");
        AssertUtil.isTrue(StringUtils.isBlank(carOrder),"请输入车牌号！");
    }

    /**
     * 车辆更新操作
     * @param car
     */
    @Transactional(propagation = Propagation.REQUIRED)  //事物
    public void updateCar(Car car){
        //1.参数校验
        //通过id查询记录
        Car temp = carMapper.selectByPrimaryKey(car.getId());
        //判断查询的记录是否为空
        AssertUtil.isTrue(null==temp,"待更新记录不存在");
        // 校验基础参数
        checkParams(car.getCarName(),car.getCarPrice(),car.getCarOrder());
        // 2. 设置相关参数值
        //判断是否已经租赁
        AssertUtil.isTrue(temp.getCarState()!=1,"当前车辆，不可修改");
        // 3.执行更新 判断结果
        AssertUtil.isTrue(carMapper.updateByPrimaryKeySelective(car)<1,"车辆数据更新失败！");
    }


    /**
     * 批量删除操作
     * @param ids
     */
    public void deleteCar(Integer[] ids) {
        //判断要删除的id是否为空
        AssertUtil.isTrue(null==ids || ids.length==0,"请选择需要删除的数据");
        for (Integer id:ids) {
            //判断是否已经租赁
            AssertUtil.isTrue(carMapper.selectByPrimaryKey(id).getCarState()!=1,"当前车辆，不可修改");
        }
        //删除数据
        AssertUtil.isTrue(carMapper.manageCarDeleteBatch(ids)<0,"车辆数据删除失败！");
    }


    //--------------------------我要租车----------------------------------
    public Map<String,Object> queryCarRent(CarRentQuery rent){
        //实例化
        Map<String,Object> map = new HashMap<>();
        // 初始化分页单位
        PageHelper.startPage(rent.getPage(),rent.getLimit());
        List<Car> cars = carMapper.RentCarSelectByParams(rent);

        //开始分页
        PageInfo<Car> pageInfo = new PageInfo<>(cars);
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }



    //批量租车
    @Transactional(propagation = Propagation.REQUIRED)
    public void rentCar(Integer[] ids,Integer userId) {
        //如果用户原始租车记录存在 首先清空原始所有租车记录 添加新的租车记录到用户车辆表
//        int count = carMapper.countUserCarByUserId(userId);
//        if(count > 0){
//            AssertUtil.isTrue(userCarMapper.deleteUserRoleByUserId(userId) != count, "租车失败！");
//        }
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要租赁的车辆！");
        for (Integer id:ids) {
            //判断改车辆是否可租赁
            AssertUtil.isTrue(carMapper.selectByPrimaryKey(id).getCarState()!=1,"有车辆不可租赁！");
            //在车辆用户表中添加记录
            UserCar userCar = new UserCar();
            userCar.setUserId(userId);
            userCar.setCarId(id);
            userCar.setRentDate(new Date());
            userCar.setReturnDate(new Date());
            AssertUtil.isTrue(carMapper.insertUserCarByParams(userCar)!=1,"租车失败！");
        }
        AssertUtil.isTrue(carMapper.changeCarState(ids,new Date()) !=ids.length, "租车失败！");
    }


}
