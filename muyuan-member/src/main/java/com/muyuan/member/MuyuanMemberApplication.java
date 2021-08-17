package com.muyuan.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MuyuanMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanMemberApplication.class, args);
    }

}
