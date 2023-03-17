package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyuan.common.mybatis.config.UserJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;

@DataSource(UserJdbcConfig.DATASOURCE_NAME)
public interface UserBaseMapper<T> extends BaseMapper<T> {


}
