package com.muyuan.member.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "member.jdbc")
public class MemberJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "member";
}
