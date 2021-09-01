package com.muyua.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MuyuanGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanGatewayApplication.class, args);
    }

}
