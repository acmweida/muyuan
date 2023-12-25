package com.muyuan.system.base.config;

import com.muyuan.common.mybatis.config.CommonJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DynamicDataSource;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.CommonReadWriteJdbcConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

}
