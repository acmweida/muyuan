package com.muyuan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com")
public class MuyuanAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanAuthApplication.class, args);
    }

}
