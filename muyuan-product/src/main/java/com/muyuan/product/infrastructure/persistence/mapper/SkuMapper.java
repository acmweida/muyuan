package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.product.domains.model.Sku;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkuMapper extends ProductBaseMapper<Sku> {


}
