package com.synway.yth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
