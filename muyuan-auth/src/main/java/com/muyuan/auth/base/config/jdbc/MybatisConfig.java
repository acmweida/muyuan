package com.muyuan.auth.base.config.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Bean
    public DataSource dataSource(AuthJdbcConfig jdbcConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
        dataSource.setJdbcUrl(jdbcConfig.getUrl());
        dataSource.setUsername(jdbcConfig.getUsername());
        dataSource.setPassword(jdbcConfig.getPassword());
        dataSource.setMaximumPoolSize(32);
        dataSource.setMinimumIdle(8);
        return dataSource;
    }
}
