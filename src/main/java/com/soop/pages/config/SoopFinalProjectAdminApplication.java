package com.soop.pages.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.soop")
@MapperScan(basePackages = "com.soop", annotationClass = Mapper.class)
public class SoopFinalProjectAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoopFinalProjectAdminApplication.class, args);
    }

}
