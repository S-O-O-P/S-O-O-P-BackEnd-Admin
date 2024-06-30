package com.soop.pages.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.soop.pages")
@MapperScan(basePackages = "com.soop.pages.dashboard.model.dao")
@MapperScan(basePackages = "com.soop.pages.customer.model.dao")
@MapperScan(basePackages = "com.soop.pages.honeypot.model.dao")
@MapperScan(basePackages = "com.soop.pages.login.model.dao")
@MapperScan(basePackages = "com.soop.pages.inquiry.model.dao")
public class SoopFinalProjectAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoopFinalProjectAdminApplication.class, args);
    }

}
