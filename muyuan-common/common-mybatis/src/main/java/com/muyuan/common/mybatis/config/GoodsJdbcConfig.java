package com.muyuan.common.mybatis.config;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "goods.jdbc")
public class GoodsJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "goods";
}
