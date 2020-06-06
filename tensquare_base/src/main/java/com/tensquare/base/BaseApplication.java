package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import util.IdWorker;

//启用Eureka客户端
@EnableEurekaClient
//开启spring定时插件
@EnableScheduling
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker(1,1);
    }
}
