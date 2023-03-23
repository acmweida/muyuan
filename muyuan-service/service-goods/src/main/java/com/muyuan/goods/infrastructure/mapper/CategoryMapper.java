package com.muyuan.goods.infrastructure.mapper;

import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import com.muyuan.goods.infrastructure.dataobject.CategoryDO;

import java.util.List;

/**
 * 商品分类Mapper接口
 *
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 */
public interface CategoryMapper extends GoodsBaseMapper<CategoryDO> {

    String ANCESTORS = "ancestors";
    Object[] STATUS_OK = new String[]{"0","1"};


    List<CategoryDO> selectByBrandId(Long... brandIds);

}
