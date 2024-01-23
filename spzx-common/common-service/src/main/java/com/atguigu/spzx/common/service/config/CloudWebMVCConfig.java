package com.atguigu.spzx.common.service.config;

import com.atguigu.spzx.common.service.interceptor.CloudLoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CloudWebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private CloudLoginAuthInterceptor cloudLoginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cloudLoginAuthInterceptor)
                .addPathPatterns("/**");
    }
}
