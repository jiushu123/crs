package com.yjxxt.crs.interceptors;

import com.yjxxt.crs.exceptions.NoLoginException;
import com.yjxxt.crs.service.UserService;
import com.yjxxt.crs.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户是否是登录状态
 * 获取Cookie对象，解析用户ID的值
 * 如果用户ID不为空，且在数据库中存在对应的用户记录，表示请求合法
 * 否则，请求不合法，进行拦截，重定向到登录页面
 */
public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取Cookie中的用户ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie((request));
        // 判断用户ID是否不为空，且数据库中存在对应的用户记录
        if (userId== null || userService.selectByPrimaryKey(userId)==null){
            // 未登录 抛出异常
            throw new NoLoginException();

        }
        return true;
    }
}
