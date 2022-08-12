package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.product.domains.dto.BrandDTO;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;

import java.util.List;

/**
 * 品牌Mapper接口
 * 
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
public interface BrandMapper extends ProductBaseMapper<Brand> {

    List<Brand> selectBy(BrandDTO brandDTO);
}
