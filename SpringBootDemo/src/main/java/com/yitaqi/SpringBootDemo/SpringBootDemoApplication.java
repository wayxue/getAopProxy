package com.yitaqi.SpringBootDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xue
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yitaqi.controller", "com.yitaqi.service"})
@MapperScan(basePackages = "com.yitaqi.dao")
public class SpringBootDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
