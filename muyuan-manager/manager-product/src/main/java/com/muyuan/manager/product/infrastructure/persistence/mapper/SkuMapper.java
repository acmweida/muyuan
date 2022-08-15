package com.muyuan.manager.product.infrastructure.persistence.mapper;

import com.muyuan.manager.product.domains.model.Sku;
import com.muyuan.manager.product.infrastructure.config.mybatis.ProductBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkuMapper extends ProductBaseMapper<Sku> {


}
