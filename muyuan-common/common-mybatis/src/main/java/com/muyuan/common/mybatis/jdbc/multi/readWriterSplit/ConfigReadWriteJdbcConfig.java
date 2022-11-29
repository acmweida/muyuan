package com.muyuan.common.mybatis.jdbc.multi.readWriterSplit;

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
@ConfigurationProperties(prefix = "config.jdbc.muti")
@ConditionalOnProperty(prefix = "config.jdbc",value = "muti")
public class ConfigReadWriteJdbcConfig extends ReadWriteJdbcConfig {

    public static final String DATASOURCE_NAME = "user";

}
