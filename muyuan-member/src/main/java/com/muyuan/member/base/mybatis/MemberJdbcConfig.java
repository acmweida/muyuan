package com.muyuan.member.base.mybatis;

import com.muyuan.common.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "member.jdbc")
public class MemberJdbcConfig extends JdbcConfig {
}
