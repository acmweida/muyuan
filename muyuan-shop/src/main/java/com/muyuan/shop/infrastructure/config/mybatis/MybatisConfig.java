package com.muyuan.shop.infrastructure.config.mybatis;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.MutiDataSourceConfig;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ReadWriteJdbcConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@MapperScan("com.muyuan.shop.infrastructure.persistence.mapper")
public class MybatisConfig {

    @Value("${db.read-method-prefix:select,find,get}")
    private List<String> readMethodPrefix;

    @Value("${db.write-method-prefix:insert,update,delete,del}")
    private List<String> writeMethodPrefix;

    @Bean
    public DataSource dataSource(ShopJdbcConfig jdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();

        HikariDataSource authDataSource = new HikariDataSource();
        authDataSource.setDriverClassName(jdbcConfig.getDriverClassName());
        authDataSource.setJdbcUrl(jdbcConfig.getUrl());
        authDataSource.setUsername(jdbcConfig.getUsername());
        authDataSource.setPassword(jdbcConfig.getPassword());
        authDataSource.setMaximumPoolSize(4);
        authDataSource.setMinimumIdle(8);
        authDataSource.setMaxLifetime(30 * 1000);
        dataSourceMap.put(ShopJdbcConfig.DATASOURCE_NAME,authDataSource);

        dataSources.setTargetDataSources(dataSourceMap);
        dataSources.setDefaultTargetDataSource(authDataSource);
        return dataSources;
    }

    @Bean
    @ConditionalOnBean(ShopReadWriteJdbcConfig.class)
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dataSource(ShopReadWriteJdbcConfig systemJdbcConfig) {
        DynamicDataSource dataSources = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();


        MutiDataSourceConfig config = new MutiDataSourceConfig();
        config.setWriteMethodPrefix(writeMethodPrefix);
        config.setReadMethodPrefix(readMethodPrefix);

        dataSourceMap.putAll(addDataSourceConfig(systemJdbcConfig,ShopReadWriteJdbcConfig.DATASOURCE_NAME,config));


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
            dataSourceId = String.format("%s%s[%s]",GlobalConst.MASTER_PREFIX,dataSourceName,i++);
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
