package com.muyuan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MuyuanAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanAuthApplication.class, args);
    }

}
