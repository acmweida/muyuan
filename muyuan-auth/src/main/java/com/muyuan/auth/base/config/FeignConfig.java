package com.muyuan.auth.base.config;

import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Encoder feignEncoder(HttpMessageConverters httpMessageConverters) {
        return new SpringEncoder(() -> httpMessageConverters);

    }
}
