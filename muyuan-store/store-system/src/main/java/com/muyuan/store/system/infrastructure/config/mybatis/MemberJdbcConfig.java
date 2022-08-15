package com.muyuan.store.system.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "member.jdbc")
@ConditionalOnProperty(prefix = "member.jdbc",value = "username")
public class MemberJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "member";
}
