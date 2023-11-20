package com.muyuan.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Oouth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oouth2Application.class, args);
    }

}
