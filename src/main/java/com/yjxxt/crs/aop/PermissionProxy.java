package com.yjxxt.crs.aop;

import com.yjxxt.crs.exceptions.NoAuthException;
import com.yjxxt.crs.exceptions.NoLoginException;
import com.yjxxt.crs.annotations.RequiredPermission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {


    @Autowired
    private HttpSession session;

    //切点:拥有com.yjxxt.crm.annotation.RequiredPermission注解的所有方法
    @Around(value="@annotation(com.yjxxt.crs.annotations.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //判断是否登录
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if(permissions == null || permissions.size()==0){
            throw new NoLoginException("未登录");
        }
        //判断是否有访问目标资源的权限码
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        //比对当前用户session域中的权限吗是否包含访问的页面上注解的code码
        if(!(permissions.contains(requiredPermission.code()))){
            //抛无权限异常
            throw  new NoAuthException("无权限访问");
        }
        Object result = pjp.proceed();

        return result;
    }
}
