package com.muyuan.config.config;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.config.ConfigJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.MutiDataSourceConfig;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ConfigReadWriteJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ReadWriteJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.UserReadWriteJdbcConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@MapperScan("com.muyuan.config.repo.mapper")
@Import({ConfigJdbcConfig.class, ConfigReadWriteJdbcConfig.class})
public class MybatisConfig {

    @Bean
    public DataSource dataSource(ConfigJdbcConfig jdbcConfig) {
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
        dataSourceMap.put(ConfigJdbcConfig.DATASOURCE_NAME,memberDataSource);

        dataSources.setTargetDataSources(dataSourceMap);
        dataSources.setDefaultTargetDataSource(memberDataSource);
        return dataSources;
    }

    @Bean
    public DataSource dataSource(ConfigReadWriteJdbcConfig userJdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();


        MutiDataSourceConfig config = new MutiDataSourceConfig();
        config.setWriteMethodPrefix(userJdbcConfig.getWriteMethodPrefix());
        config.setReadMethodPrefix(userJdbcConfig.getReadMethodPrefix());

        dataSourceMap.putAll(addDataSourceConfig(userJdbcConfig,UserReadWriteJdbcConfig.DATASOURCE_NAME,config));


        dataSources.setMutiDateSourceConfig(config);
        dataSources.setTargetDataSources(dataSourceMap);

        dataSources.setDefaultTargetDataSource(config.getDefaultDateSource());
        return dataSources;
    }


    private Map<Object,Object> addDataSourceConfig(ReadWriteJdbcConfig readWriteJdbcConfig, String dataSourceName, MutiDataSourceConfig mutiDataSourceConfig) {
        Map<Object,Object> dataSourceMap = new HashMap<>();
        List<JdbcConfig> masters = readWriteJdbcConfig.getMasters();
        List<JdbcConfig> slaves = readWriteJdbcConfig.getSlaves();

        Assert.isTrue(masters.size() > 0,"master jdbc config not found");
        Assert.isTrue(slaves.size() > 0,"slaves jdbc config not found");

        List<String> masterIds = new ArrayList<>();
        List<String> slaveIds = new ArrayList<>();

        int i=0;
        String dataSourceId;
        for (JdbcConfig jdbcConfig : masters) {

            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
            dataSource.setJdbcUrl(jdbcConfig.getUrl());
            dataSource.setUsername(jdbcConfig.getUsername());
            dataSource.setPassword(jdbcConfig.getPassword());
            dataSource.setMaximumPoolSize(4);
            dataSource.setMinimumIdle(8);
            dataSource.setMaxLifetime(30 * 1000);

            if (i == 0) {
                mutiDataSourceConfig.setDefaultDateSource(dataSource);
            }
            dataSourceId = String.format("%s%s[%s]", GlobalConst.MASTER_PREFIX,dataSourceName,i++);
            dataSourceMap.put(dataSourceId, dataSource);
            masterIds.add(dataSourceId);
        }

        i=0;
        for (JdbcConfig jdbcConfig : slaves) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
            dataSource.setJdbcUrl(jdbcConfig.getUrl());
            dataSource.setUsername(jdbcConfig.getUsername());
            dataSource.setPassword(jdbcConfig.getPassword());
            dataSource.setMaximumPoolSize(4);
            dataSource.setMinimumIdle(8);
            dataSource.setMaxLifetime(30 * 1000);
            dataSourceId = String.format("%s%s[%s]", GlobalConst.SLAVE_PREFIX,dataSourceName,i++);
            dataSourceMap.put(dataSourceId, dataSource);
            slaveIds.add(dataSourceId);
        }

        mutiDataSourceConfig.getReadWriteSplit().put(GlobalConst.MASTER_PREFIX + dataSourceName,masterIds);
        mutiDataSourceConfig.getReadWriteSplit().put(GlobalConst.SLAVE_PREFIX + dataSourceName,slaveIds);


        return dataSourceMap;
    }
}
