package com.muyuan.user.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.muyuan.user.infrastructure.repo.mapper")
//@Import({UserJdbcConfig.class,UserReadWriteJdbcConfig.class})
public class MybatisConfig {

//    @Bean
////    @ConditionalOnBean(UserJdbcConfig.class)
//    public DataSource dataSource(UserJdbcConfig jdbcConfig) {
//        DynamicDataSource dataSources = new DynamicDataSource();
//        Map<Object,Object> dataSourceMap = new HashMap<>();
//
//        HikariDataSource memberDataSource = new HikariDataSource();
//        memberDataSource.setDriverClassName(jdbcConfig.getDriverClassName());
//        memberDataSource.setJdbcUrl(jdbcConfig.getUrl());
//        memberDataSource.setUsername(jdbcConfig.getUsername());
//        memberDataSource.setPassword(jdbcConfig.getPassword());
//        memberDataSource.setMaximumPoolSize(4);
//        memberDataSource.setMinimumIdle(8);
//        memberDataSource.setMaxLifetime( 30 * 1000);
//        dataSourceMap.put(UserJdbcConfig.DATASOURCE_NAME,memberDataSource);
//
//        dataSources.setTargetDataSources(dataSourceMap);
//        dataSources.setDefaultTargetDataSource(memberDataSource);
//        return dataSources;
//    }
//
//    @Bean
////    @ConditionalOnClass(UserReadWriteJdbcConfig.class)
//    public DataSource dataSource(UserReadWriteJdbcConfig userJdbcConfig) {
//        DynamicDataSource dataSources = new DynamicDataSource();
//        Map<Object,Object> dataSourceMap = new HashMap<>();
//
//
//        MutiDataSourceConfig config = new MutiDataSourceConfig();
//        config.setWriteMethodPrefix(userJdbcConfig.getWriteMethodPrefix());
//        config.setReadMethodPrefix(userJdbcConfig.getReadMethodPrefix());
//
//        dataSourceMap.putAll(addDataSourceConfig(userJdbcConfig,UserReadWriteJdbcConfig.DATASOURCE_NAME,config));
//
//
//        dataSources.setMutiDateSourceConfig(config);
//        dataSources.setTargetDataSources(dataSourceMap);
//
//        dataSources.setDefaultTargetDataSource(config.getDefaultDateSource());
//        return dataSources;
//    }
//
//
//    private Map<Object,Object> addDataSourceConfig(ReadWriteJdbcConfig readWriteJdbcConfig, String dataSourceName, MutiDataSourceConfig mutiDataSourceConfig) {
//        Map<Object,Object> dataSourceMap = new HashMap<>();
//        List<JdbcConfig> masters = readWriteJdbcConfig.getMasters();
//        List<JdbcConfig> slaves = readWriteJdbcConfig.getSlaves();
//
//        Assert.isTrue(masters.size() > 0,"master jdbc config not found");
//        Assert.isTrue(slaves.size() > 0,"slaves jdbc config not found");
//
//        List<String> masterIds = new ArrayList<>();
//        List<String> slaveIds = new ArrayList<>();
//
//        int i=0;
//        String dataSourceId;
//        for (JdbcConfig jdbcConfig : masters) {
//
//            HikariDataSource dataSource = new HikariDataSource();
//            dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
//            dataSource.setJdbcUrl(jdbcConfig.getUrl());
//            dataSource.setUsername(jdbcConfig.getUsername());
//            dataSource.setPassword(jdbcConfig.getPassword());
//            dataSource.setMaximumPoolSize(4);
//            dataSource.setMinimumIdle(8);
//            dataSource.setMaxLifetime(30 * 1000);
//
//            if (i == 0) {
//                mutiDataSourceConfig.setDefaultDateSource(dataSource);
//            }
//            dataSourceId = String.format("%s%s[%s]", GlobalConst.MASTER_PREFIX,dataSourceName,i++);
//            dataSourceMap.put(dataSourceId, dataSource);
//            masterIds.add(dataSourceId);
//            i++;
//        }
//
//        i=0;
//        for (JdbcConfig jdbcConfig : slaves) {
//            HikariDataSource dataSource = new HikariDataSource();
//            dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
//            dataSource.setJdbcUrl(jdbcConfig.getUrl());
//            dataSource.setUsername(jdbcConfig.getUsername());
//            dataSource.setPassword(jdbcConfig.getPassword());
//            dataSource.setMaximumPoolSize(4);
//            dataSource.setMinimumIdle(8);
//            dataSource.setMaxLifetime(30 * 1000);
//            dataSourceId = String.format("%s%s[%s]",GlobalConst.SLAVE_PREFIX,dataSourceName,i++);
//            dataSourceMap.put(dataSourceId, dataSource);
//            slaveIds.add(dataSourceId);
//        }
//
//        mutiDataSourceConfig.getReadWriteSplit().put(GlobalConst.MASTER_PREFIX + dataSourceName,masterIds);
//        mutiDataSourceConfig.getReadWriteSplit().put(GlobalConst.SLAVE_PREFIX + dataSourceName,slaveIds);
//
//
//        return dataSourceMap;
//    }
}
