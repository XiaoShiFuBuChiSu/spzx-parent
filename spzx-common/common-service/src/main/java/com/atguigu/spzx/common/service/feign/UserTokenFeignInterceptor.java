package com.atguigu.spzx.common.service.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class UserTokenFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 通过RequestContextHolder获取到当前请求的request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // token获取请求头
        String token = requestAttributes.getRequest().getHeader("token");
        // 添加到RequestTemplate中，使feign能够将token传递到服务端
        if (token != null) {
            template.header("token", token);
        }

    }
}
