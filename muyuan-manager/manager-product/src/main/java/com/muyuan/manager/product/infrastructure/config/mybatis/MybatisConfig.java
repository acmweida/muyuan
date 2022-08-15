package com.muyuan.manager.product.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@MapperScan("com.muyuan.product.infrastructure.persistence.mapper")
public class MybatisConfig {

    @Value("${db.read-method-prefix:select,find,get}")
    private List<String> readMethodPrefix;

    @Value("${db.write-method-prefix:insert,update,delete,del}")
    private List<String> writeMethodPrefix;

    @Bean
    public DataSource dataSource(ProductJdbcConfig jdbcConfig) {
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
        dataSourceMap.put(ProductJdbcConfig.DATASOURCE_NAME,memberDataSource);

        dataSources.setTargetDataSources(dataSourceMap);
        dataSources.setDefaultTargetDataSource(memberDataSource);
        return dataSources;
    }
}
