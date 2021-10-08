package com.muyuan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.muyuan"})
@EnableFeignClients(basePackages = {"com.muyuan"})
public class MuyuanAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanAuthApplication.class, args);
    }

}
