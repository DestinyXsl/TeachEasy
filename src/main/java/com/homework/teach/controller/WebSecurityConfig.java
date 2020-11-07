package com.homework.teach.controller;


import com.homework.teach.util.RedisComponentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";
    @Autowired
    public RedisComponentUtil redisComponentUtil;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration = registry.addInterceptor(new SecurityInterceptor());     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");                    //所有路径都被拦截
        registration.excludePathPatterns("/login","/error","/static/**","/logout","/vendor/**","/js/**","/css/**","/loginPost","/homework/beRegisterHomework");       //添加不拦截路径
//        super.addInterceptors(registry);


        //权限拦截的管理器
//        InterceptorRegistration registration1 = registry.addInterceptor(rightsInterceptor);
//        registration1.addPathPatterns("/**");                    //所有路径都被拦截
//        registration1.excludePathPatterns("/","/login","/error","/static/**","/logout");       //添加不拦截路径
    }
    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            System.out.println(request.getRequestURI());
            System.out.println(request.getRequestURL().toString());
            System.out.println(request.getServletPath());
//            String token = request.getHeader("X-Access-Token");
//            if(!redisComponentUtil.get(token).equals(""))
//                return true;
            if (session.getAttribute(SESSION_KEY) != null)
                return true;

            // 跳转登录
            String url = "/login";
            response.sendRedirect(url);
            return false;
        }
    }
}