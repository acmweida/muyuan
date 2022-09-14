package com.muyuan.manager.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication()
@EnableDiscoveryClient
public class ManagerAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerAuthApplication.class, args);
    }

}
