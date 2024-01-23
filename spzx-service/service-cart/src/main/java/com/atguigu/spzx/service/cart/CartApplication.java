package com.atguigu.spzx.service.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients("com.atguigu.spzx")
@ComponentScan("com.atguigu.spzx")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})   // 不连接MySQL数据库，排除数据源自动配置类
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}