package com.muyuan.auth.base.config.jdbc;

import com.muyuan.common.repo.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth.jdbc")
public class AuthJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "auth";
}
