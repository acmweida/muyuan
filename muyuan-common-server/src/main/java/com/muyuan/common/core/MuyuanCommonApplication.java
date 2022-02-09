package com.muyuan.common.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.muyuan"})
public class MuyuanCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanCommonApplication.class, args);
    }

}
