package com.muyuan.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName MuyuanConfigApplication
 * Description 配置服务
 * @Author 2456910384
 * @Date 2022/10/14 9:46
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConfigApplication.class, args);
    }
}
