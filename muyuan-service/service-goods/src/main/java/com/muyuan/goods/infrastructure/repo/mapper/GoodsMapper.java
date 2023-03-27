package com.muyuan.goods.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import com.muyuan.goods.infrastructure.repo.dataobject.GoodsDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper extends GoodsBaseMapper<GoodsDO> {


}
