package com.muyuan.common.mybatis.config;

import com.muyuan.common.mybatis.jdbc.page.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Bean
    public Interceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
