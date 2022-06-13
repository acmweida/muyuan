package com.muyuan.system.infrastructure.config.mybatis;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ReadWriteJdbcConfig;
import com.muyuan.common.mybatis.jdbc.page.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@MapperScan("com.muyuan.system.infrastructure.persistence.mapper")
public class MybatisConfig {

    @Bean
    @ConditionalOnBean(SystemJdbcConfig.class)
    public DataSource dataSource(SystemJdbcConfig jdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();

        HikariDataSource memberDataSource = new HikariDataSource();
        memberDataSource.setDriverClassName(jdbcConfig.getDriverClassName());
        memberDataSource.setJdbcUrl(jdbcConfig.getUrl());
        memberDataSource.setUsername(jdbcConfig.getUsername());
        memberDataSource.setPassword(jdbcConfig.getPassword());
        memberDataSource.setMaximumPoolSize(4);
        memberDataSource.setMinimumIdle(8);
        memberDataSource.setMaxLifetime( 60 * 60 * 1000);
        dataSourceMap.put(SystemJdbcConfig.DATASOURCE_NAME,memberDataSource);

        dataSources.setTargetDataSources(dataSourceMap);
        dataSources.setDefaultTargetDataSource(memberDataSource);
        return dataSources;
    }

    @Bean
    @ConditionalOnBean(ReadWriteJdbcConfig.class)
    public DataSource dataSource(SystemReadWriteJdbcConfig systemJdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();

        dataSourceMap.putAll(addDataSourceConfig(systemJdbcConfig));

        dataSources.setTargetDataSources(dataSourceMap);
        return dataSources;
    }


    private Map<Object,Object> addDataSourceConfig(ReadWriteJdbcConfig readWriteJdbcConfig) {
        Map<Object,Object> dataSourceMap = new HashMap<>();
        List<JdbcConfig> masters = readWriteJdbcConfig.getMasters();
        List<JdbcConfig> slaves = readWriteJdbcConfig.getSlaves();

        Assert.isTrue(masters.size() > 0,"master jdbc config not found");
        Assert.isTrue(slaves.size() > 0,"slaves jdbc config not found");

        for (JdbcConfig jdbcConfig : masters) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
            dataSource.setJdbcUrl(jdbcConfig.getUrl());
            dataSource.setUsername(jdbcConfig.getUsername());
            dataSource.setPassword(jdbcConfig.getPassword());
            dataSource.setMaximumPoolSize(4);
            dataSource.setMinimumIdle(8);
            dataSource.setMaxLifetime(60 * 60 * 1000);
            dataSourceMap.put(GlobalConst.MASTER_PREFIX+SystemJdbcConfig.DATASOURCE_NAME, dataSource);
        }

        for (JdbcConfig jdbcConfig : slaves) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
            dataSource.setJdbcUrl(jdbcConfig.getUrl());
            dataSource.setUsername(jdbcConfig.getUsername());
            dataSource.setPassword(jdbcConfig.getPassword());
            dataSource.setMaximumPoolSize(4);
            dataSource.setMinimumIdle(8);
            dataSource.setMaxLifetime(60 * 60 * 1000);
            dataSourceMap.put(GlobalConst.SLAVE_PREFIX+SystemJdbcConfig.DATASOURCE_NAME, dataSource);
        }

        return dataSourceMap;
    }




    @Bean
    @Qualifier("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addInterceptor(new PageInterceptor());
        sqlSessionFactoryBean.setConfiguration(configuration);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }
}
