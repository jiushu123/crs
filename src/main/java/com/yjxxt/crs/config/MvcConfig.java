package com.yjxxt.crs.config;

import com.yjxxt.crs.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器生效的配置类 ,需加@Configuration注解
 * */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 配置
    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }


    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要一个实现HandlerInterceptor接口的拦截器实例，这里使用的是NoLoginInterceptor

        // 调用配置的方法 添加拦截器
        registry.addInterceptor(noLoginInterceptor())
                // 设置拦截的路径
                .addPathPatterns("/**")
                // 设置放行的路径  bug："/li b/**" 导致未放行，登录页面发生了变化
                .excludePathPatterns("/index","/user/login","/css/**","/images/**","/js/**","/lib/**");

    }
}
