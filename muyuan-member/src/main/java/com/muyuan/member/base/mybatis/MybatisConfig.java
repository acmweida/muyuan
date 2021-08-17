package com.muyuan.member.base.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.nacosprovider.dao")
public class MybatisConfig {


//    @Bean
//    public DataSource dataSource(MemberJdbcConfig jdbcConfig) {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
//        dataSource.setJdbcUrl(jdbcConfig.getUrl());
//        dataSource.setUsername(jdbcConfig.getUsername());
//        dataSource.setPassword(jdbcConfig.getPassword());
//        dataSource.setMaximumPoolSize(32);
//        dataSource.setMinimumIdle(8);
//        return dataSource;
//    }
}
