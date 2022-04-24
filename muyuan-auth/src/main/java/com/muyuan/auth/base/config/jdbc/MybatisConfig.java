package com.muyuan.auth.base.config.jdbc;

import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Bean
    public DataSource dataSource(AuthJdbcConfig jdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();

        HikariDataSource authDataSource = new HikariDataSource();
        authDataSource.setDriverClassName(jdbcConfig.getDriverClassName());
        authDataSource.setJdbcUrl(jdbcConfig.getUrl());
        authDataSource.setUsername(jdbcConfig.getUsername());
        authDataSource.setPassword(jdbcConfig.getPassword());
        authDataSource.setMaximumPoolSize(4);
        authDataSource.setMinimumIdle(8);
        dataSourceMap.put(AuthJdbcConfig.DATASOURCE_NAME,authDataSource);

        dataSources.setTargetDataSources(dataSourceMap);
        dataSources.setDefaultTargetDataSource(authDataSource);
        return dataSources;
    }
}
