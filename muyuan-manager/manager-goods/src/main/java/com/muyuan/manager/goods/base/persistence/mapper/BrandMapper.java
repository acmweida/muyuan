package com.muyuan.manager.goods.base.persistence.mapper;

import com.muyuan.manager.goods.domains.dto.BrandDTO;
import com.muyuan.manager.goods.domains.model.Brand;
import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;

import java.util.List;

/**
 * 品牌Mapper接口
 * 
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
public interface BrandMapper extends GoodsBaseMapper<Brand> {

    List<Brand> selectBy(BrandDTO brandDTO);
}
