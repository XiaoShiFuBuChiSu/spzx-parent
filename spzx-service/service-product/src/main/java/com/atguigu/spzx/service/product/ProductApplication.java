package com.atguigu.spzx.service.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.atguigu.spzx.service.product.mapper")
@ComponentScan({"com.atguigu.spzx.common.service.execption","com.atguigu.spzx.service.product"})
@EnableCaching      // 开启缓存注解驱动
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
