package com.muyuan.system.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.readWriterSplit.ReadWriteJdbcConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConfigurationProperties(prefix = "system.jdbc.masters")
@ConditionalOnProperty("system.jdbc.masters")
public class SystemReadWriteJdbcConfig extends ReadWriteJdbcConfig {

    public static final String DATASOURCE_NAME = "system";
}
