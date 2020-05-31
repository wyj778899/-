package com.xzj.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xzj.car.dao")
//MapperScan指定dao接口的包路径让spring扫描
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
