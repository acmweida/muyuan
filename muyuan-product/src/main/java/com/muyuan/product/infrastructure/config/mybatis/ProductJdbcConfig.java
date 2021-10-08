package com.muyuan.product.infrastructure.config.mybatis;

import com.muyuan.common.repo.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "product.jdbc")
public class ProductJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "product";
}
