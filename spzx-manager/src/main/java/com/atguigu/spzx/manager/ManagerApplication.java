package com.atguigu.spzx.manager;

import com.atguigu.spzx.model.properties.AuthExcludeUrlProperty;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.atguigu.spzx")  // 修改包扫描，可以扫描到其他工程中的Bean
@MapperScan("com.atguigu.spzx.manager.mapper")
@EnableConfigurationProperties(AuthExcludeUrlProperty.class)
// @Import(value = {GlobalResultException.class})
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

}