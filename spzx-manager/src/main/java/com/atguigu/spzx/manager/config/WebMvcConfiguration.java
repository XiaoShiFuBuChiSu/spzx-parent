package com.atguigu.spzx.manager.config;

import com.atguigu.spzx.common.service.interceptor.LoginAuthInterceptor;
import com.atguigu.spzx.model.properties.AuthExcludeUrlProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Web处理
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private AuthExcludeUrlProperty authExcludeUrlProperty;

    /**
     * 自定义跨域处理
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");                // 允许所有的请求头
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls = authExcludeUrlProperty.getUrls();
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns(CollectionUtils.isEmpty(urls) ? new ArrayList() : urls)
                .addPathPatterns("/**");
    }
}