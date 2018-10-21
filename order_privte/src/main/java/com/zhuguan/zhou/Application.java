package com.zhuguan.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker//服务降级开注解服务熔断不需要
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
