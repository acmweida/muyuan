package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyuan.common.mybatis.config.SystemJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;

@DataSource(SystemJdbcConfig.DATASOURCE_NAME)
public interface SystemBaseMapper<T> extends BaseMapper<T> {


}
