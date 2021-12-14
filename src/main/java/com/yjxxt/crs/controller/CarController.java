package com.yjxxt.crs.controller;

import com.yjxxt.crs.annotations.RequiredPermission;
import com.yjxxt.crs.base.BaseController;
import com.yjxxt.crs.base.ResultInfo;
import com.yjxxt.crs.bean.Car;
import com.yjxxt.crs.query.CarQuery;
import com.yjxxt.crs.query.CarRentQuery;
import com.yjxxt.crs.query.ManageCarQuery;
import com.yjxxt.crs.service.CarService;
import com.yjxxt.crs.utils.LoginUserUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("car")
public class CarController extends BaseController {


    @Resource
    private CarService carService;

    @RequestMapping("rent_car")
    public String index() {
        return "car/rent_car";
    }

    @RequiredPermission(code = "10")
    @RequestMapping("scan_car")
    public String scan() {
        return "car/scan_car";
    }


    /**
     * 选择车辆还车  批量还车
     *
     * @param ids
     * @return
     */
    @RequiredPermission(code = "1011")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids) {
        // 还车
        carService.deleteSaleChance(ids);
        return success("还车成功！");
    }


    /***
     *
     * 多条件分页查询车辆
     * @param query
     *  @return
     *
     */
    //------------------------------我的车辆---------------------------------------

    @RequiredPermission(code = "1012")
    @RequestMapping("scan_list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams (CarQuery query,HttpServletRequest request) {

        // 获取当前登录用户的ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        return carService.querySaleChanceByParams( query,userId);
    }




    //-----------------------------车辆设置-----------------------------------------

    /**
     * 进入我要租车页面
     * @return
     */
    @RequiredPermission(code = "60")
    @RequestMapping("management_car")
    public String car_rental(){
        return "manageCar/car_rental";
    }

    /**
     * 车辆列表展示
     * @param query
     * @return
     */
    @RequiredPermission(code = "6062")
    @RequestMapping("manageCar_list")
    @ResponseBody
    public Map<String,Object> sayList(ManageCarQuery query){
        return carService.queryCarRentalByParams(query);
    }

    /**
     * 车辆数据的添加
     * @param req
     * @param car
     * @return
     */
    @RequiredPermission(code = "6061")
    @RequestMapping("manageCar_save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest req, Car car){
        //通过cookie获取用户的userId
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //添加车辆的数据
        carService.addCar(car);
        //返回目标对象ResultInfo
        return success("添加成功！");
    }

    /**
     * 机会数据添加与更新页面视图转发
     *      id为空  添加操作
     *      id非空  修改操作
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("manageCar_addOrUpdateCarPage")
    public String addOrUpdateCarPage(Integer id, Model model){
        //如果id不为空，表示是修改操作，修改操作需要查询被修改的数据
        if(null!=id){
            //通过id查询汽车信息
            Car car = carService.selectByPrimaryKey(id);
            //将数据存到作用域中
            model.addAttribute("car",car);
        }
        return "manageCar/add_update";
    }

    @RequiredPermission(code = "6063")
    @RequestMapping("manageCar_update")
    @ResponseBody
    public ResultInfo updateCar(Car car){
        //更新车辆的数据
        carService.updateCar(car);
        return success("更新成功！");
    }

    /**
     * 批量删除车辆
     * @param ids
     * @return
     */
    @RequiredPermission(code = "6064")
    @RequestMapping("manageCar_delete")
    @ResponseBody
    public ResultInfo deleteCar(Integer[] ids){
        //删除车辆
        carService.deleteCar(ids);
        return success("删除成功！");
    }



    //---------------------------我要租车----------------------------------------


    /*
    多条件分页查询营销机会

     */
    @RequiredPermission(code = "20")
    @RequestMapping("RentCar_list")
    @ResponseBody
    public Map<String,Object> queryCarRent(CarRentQuery rent){
        Map<String, Object> map = carService.queryCarRent(rent);
        return map;
    }
    /*
    批量租车
     */
    @RequiredPermission(code = "2021")
    @RequestMapping("RentCar")
    @ResponseBody
    public ResultInfo addCar(Integer[] ids,HttpServletRequest req){
        //通过cookie获取用户的userId
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //批量租车状态改变,用户车辆记录添加
        carService.rentCar(ids,userId);
        return success("租车成功");
    }


}
