package com.muyuan.product;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCreateCacheAnnotation
@SpringBootApplication
public class MuyuanProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanProductApplication.class, args);
    }

}
