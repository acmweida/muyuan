package com.muyuan.common.base.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "common.jdbc")
public class CommonJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "product";
}
