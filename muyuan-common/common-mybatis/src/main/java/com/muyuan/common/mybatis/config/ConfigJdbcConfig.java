package com.muyuan.common.mybatis.config;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.jdbc")
@ConditionalOnProperty(prefix = "config.jdbc",value = "username")
public class ConfigJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "config";
}
