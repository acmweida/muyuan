package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyuan.common.mybatis.config.GoodsJdbcConfig;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;

@DataSource(GoodsJdbcConfig.DATASOURCE_NAME)
public interface GoodsBaseMapper<T> extends BaseMapper<T> {




}
