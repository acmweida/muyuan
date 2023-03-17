package com.muyuan.store.shop.infrastructure.config.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;

@DataSource(ShopJdbcConfig.DATASOURCE_NAME)
public interface ShopBaseMapper<T> extends BaseMapper<T> {


}
