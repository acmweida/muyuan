package com.muyuan.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName UserApplication
 * Description 用户中心
 * @Author 2456910384
 * @Date 2022/10/11 14:19
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MuyuanUserApplication {

     public static void main(String[] args) {
        SpringApplication.run(MuyuanUserApplication.class, args);
    }
}
