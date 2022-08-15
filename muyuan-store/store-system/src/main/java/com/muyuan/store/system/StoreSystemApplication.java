package com.muyuan.store.system;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDubbo(scanBasePackages = "com.store.system.interfaces.facade.api")
public class StoreSystemApplication {

    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(StoreSystemApplication.class, args);
    }

}
