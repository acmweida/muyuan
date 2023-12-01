package com.muyuan.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CountDownLatch;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
@ComponentScan({"com.muyuan"})
public class ServiceUserApplication {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ServiceUserApplication.class, args);
        countDownLatch.await();
    }

}