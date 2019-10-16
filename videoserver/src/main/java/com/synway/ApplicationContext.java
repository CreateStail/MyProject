package com.synway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.synway.dao")
@EnableTransactionManagement
public class ApplicationContext {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class,args);
    }
}
