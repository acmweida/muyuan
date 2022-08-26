package com.muyuan.goods.infrastructure.mapper;

import com.muyuan.common.mybatis.jdbc.ProductBaseMapper;
import com.muyuan.goods.infrastructure.dateobject.GoodsDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper extends ProductBaseMapper<GoodsDO> {


}
