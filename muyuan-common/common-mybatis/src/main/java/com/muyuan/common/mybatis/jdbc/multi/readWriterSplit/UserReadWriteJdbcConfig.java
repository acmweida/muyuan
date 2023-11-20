package com.muyuan.common.mybatis.jdbc.multi.readWriterSplit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @ClassName SystemReadWriteJdbcConfig
 * Description System 读写分离配置
 * @Author 2456910384
 * @Date 2022/6/13 17:24
 * @Version 1.0
 */
@Component
@Profile("prod")
@ConfigurationProperties(prefix = "user.jdbc.muti")
public class UserReadWriteJdbcConfig extends ReadWriteJdbcConfig {

    public static final String DATASOURCE_NAME = "user";

}
