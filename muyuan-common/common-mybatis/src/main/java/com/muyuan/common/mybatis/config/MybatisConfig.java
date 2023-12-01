package com.muyuan.common.mybatis.config;

import com.muyuan.common.mybatis.id.IdGeneratorAspect;
//import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSourceAdvisor;
import com.muyuan.common.mybatis.jdbc.page.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("commonMybatisConfig")
@EnableTransactionManagement
@Import({IdGeneratorAspect.class})
public class MybatisConfig {

    @Bean
    public Interceptor pageInterceptor() {
        return new PageInterceptor();
    }

}
