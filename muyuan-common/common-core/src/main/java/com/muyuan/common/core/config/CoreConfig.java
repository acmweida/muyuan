package com.muyuan.common.core.config;

import com.muyuan.common.core.context.ApplicationContextHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Config
 * Description Spring 基础配置
 * @Author 2456910384
 * @Date 2022/7/18 15:48
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class CoreConfig {

    @Bean
    public ApplicationContextHandler applicationContextHandler() {
        return new ApplicationContextHandler();
    }
}
