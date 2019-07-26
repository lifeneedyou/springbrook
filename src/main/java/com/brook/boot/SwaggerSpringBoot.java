package com.brook.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.brook"})
@MapperScan("com.brook.mapper")
public class SwaggerSpringBoot  {

    public static void main(String[] args) throws Exception {
    	 SpringApplication.run(SwaggerSpringBoot.class, args);
    }
}
