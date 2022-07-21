package com.muyuan.shop.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ReadWriteJdbcConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName SystemReadWriteJdbcConfig
 * Description System 读写分离配置
 * @Author 2456910384
 * @Date 2022/6/13 17:24
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "shop.jdbc.muti")
@ConditionalOnMissingBean(ShopJdbcConfig.class)
public class ShopReadWriteJdbcConfig extends ReadWriteJdbcConfig {

    public static final String DATASOURCE_NAME = "system";
}
