package com.muyuan.auth.base.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName IClientConfig
 * Description TODO
 * @Author 2456910384
 * @Date 2022/2/10 11:30
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class IClientConfig {
    @Bean
    public DefaultClientConfigImpl iClientConfig(){
        return new DefaultClientConfigImpl();
    }
}
