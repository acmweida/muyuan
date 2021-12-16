package com.muyuan.member.infrastructure.config.mybatis;

import com.muyuan.common.repo.jdbc.multi.DataSource;
import com.muyuan.common.repo.jdbc.mybatis.JdbcBaseMapper;

@DataSource(MemberJdbcConfig.DATASOURCE_NAME)
public interface MemberBaseMapper<T> extends JdbcBaseMapper<T> {
}
