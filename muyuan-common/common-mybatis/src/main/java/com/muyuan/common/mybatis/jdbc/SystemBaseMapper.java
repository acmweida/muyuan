package com.muyuan.common.mybatis.jdbc;

import com.muyuan.common.mybatis.config.SystemJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import org.apache.ibatis.annotations.DeleteProvider;

@DataSource(SystemJdbcConfig.DATASOURCE_NAME)
public interface SystemBaseMapper<T> extends JdbcBaseMapper<T> {


}
