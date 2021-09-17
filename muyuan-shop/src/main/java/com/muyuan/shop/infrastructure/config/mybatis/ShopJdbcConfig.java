package com.muyuan.shop.infrastructure.config.mybatis;

import com.muyuan.common.repo.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "shop.jdbc")
public class ShopJdbcConfig extends JdbcConfig {
}
