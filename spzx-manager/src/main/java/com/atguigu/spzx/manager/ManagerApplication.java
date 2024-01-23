package com.atguigu.spzx.manager;

import com.atguigu.spzx.common.service.config.CloudWebMVCConfig;
import com.atguigu.spzx.common.service.interceptor.CloudLoginAuthInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(
        value = "com.atguigu.spzx",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CloudLoginAuthInterceptor.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CloudWebMVCConfig.class)
        }
)  // 修改包扫描，可以扫描到其他工程中的Bean
@MapperScan("com.atguigu.spzx.manager.mapper")
@EnableScheduling
@EnableAspectJAutoProxy
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

}