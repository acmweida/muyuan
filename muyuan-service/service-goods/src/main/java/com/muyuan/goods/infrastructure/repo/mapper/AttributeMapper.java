package com.muyuan.goods.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import com.muyuan.goods.infrastructure.repo.dataobject.AttributeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类属性Mapper接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Mapper
public interface AttributeMapper extends GoodsBaseMapper<AttributeDO> {

}
