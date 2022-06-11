package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ProductCategoryMapper 接口
 * Description 商品分类 Mapper
 * @Author 2456910384
 * @Date 2022/6/10 11:39
 * @Version 1.0
 */
@Mapper
public interface ProductCategoryMapper extends ProductBaseMapper<ProductCategory> {
}
