package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyuan.common.mybatis.config.CommonJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;

@DataSource(CommonJdbcConfig.DATASOURCE_NAME)
public interface CommonBaseMapper<T> extends BaseMapper<T> {


}
