package com.yjxxt.crs;

import com.alibaba.fastjson.JSON;
import com.yjxxt.crs.base.ResultInfo;
import com.yjxxt.crs.exceptions.NoLoginException;
import com.yjxxt.crs.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常统一处理
 */

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /**
     * 方法返回值类型
     * 视图
     * JSON
     * 如何判断方法的返回类型：
     * 如果方法级别配置了 @ResponseBody 注解，表示方法返回的是JSON；
     * 反之，返回的是视图页面
     * @param req
     * @param resp
     * @param handler
     * @param ex
     * @return
     */

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        //判断是否是未登录异常
        if (ex instanceof NoLoginException){
            //如果捕获的是未登录异常,则跳转登录页面
            ModelAndView ma = new ModelAndView("redirect:index");
            return ma;
        }
        //设置默认异常处理
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("code",400);
        mav.addObject("msg","系统异常,请稍后再式...");

        //判断HandlerMethod
        if (handler instanceof HandlerMethod){
            //类型转换
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的ResponseBody注解
            ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
            //判断 ResponseBody 注解是否存在(如果不存在表示返回的是视图,如果存在返回的是JSON)
            if (responseBody == null){
                /**
                 * 方法返回视图
                 */
                if (ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    mav.addObject("code",pe.getCode());
                    mav.addObject("msg",pe.getMsg());

                }
                return mav;


            }else {

                /**
                 * 方法上返回的是JSON
                 */
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统异常,请重试!");

                //如果捕获的是自定义异常
                if (ex instanceof ParamsException){

                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());

                }
                //设置响应类型和编码格式(响应JSON格式)
                resp.setContentType("application/json;charset=utf-8");

                PrintWriter writer = null;
                try {
                    writer = resp.getWriter();
                    writer.write(JSON.toJSONString(resultInfo));

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null){
                        writer.close();
                    }
                }

                return null;

            }


        }



        return mav;



    }


}