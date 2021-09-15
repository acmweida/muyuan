package com.muyuan.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MuyuanShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanShopApplication.class, args);
    }

}
