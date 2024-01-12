package com.atguigu.spzx.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.atguigu.spzx")  // 修改包扫描，可以扫描到其他工程中的Bean
@MapperScan("com.atguigu.spzx.manager.mapper")
// @EnableConfigurationProperties({AuthExcludeUrlProperty.class, MinioConfigProperty.class})
@EnableScheduling
// @Import(value = {GlobalResultException.class})
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

}