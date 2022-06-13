package com.muyuan.system.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ReadWriteJdbcConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system.jdbc")
@ConditionalOnMissingBean(SystemReadWriteJdbcConfig.class)
public class SystemJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "system";
}
