package com.brook.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xuequan
 * @Date: 2019/5/8 16:54
 * @Description:
 */
@Component
public class WebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/organize/**");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/team/**");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/project/**");
    }

}