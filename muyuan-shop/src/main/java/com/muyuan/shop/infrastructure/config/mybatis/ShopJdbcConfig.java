package com.muyuan.shop.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "shop.jdbc")
public class ShopJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "shop";
}
