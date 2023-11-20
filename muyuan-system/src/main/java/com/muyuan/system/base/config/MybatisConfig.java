package com.muyuan.system.base.config;

import com.muyuan.common.mybatis.config.CommonJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.CommonReadWriteJdbcConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@Import({CommonJdbcConfig.class, CommonReadWriteJdbcConfig.class})
public class MybatisConfig {

    @Bean
    public DataSource dataSource(CommonJdbcConfig jdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();

        HikariDataSource memberDataSource = new HikariDataSource();
        memberDataSource.setDriverClassName(jdbcConfig.getDriverClassName());
        memberDataSource.setJdbcUrl(jdbcConfig.getUrl());
        memberDataSource.setUsername(jdbcConfig.getUsername());
        memberDataSource.setPassword(jdbcConfig.getPassword());
        memberDataSource.setMaximumPoolSize(4);
        memberDataSource.setMinimumIdle(8);
        memberDataSource.setMaxLifetime( 30 * 1000);
        dataSourceMap.put(CommonJdbcConfig.DATASOURCE_NAME,memberDataSource);

        dataSources.setTargetDataSources(dataSourceMap);
        dataSources.setDefaultTargetDataSource(memberDataSource);
        return dataSources;
    }
}
