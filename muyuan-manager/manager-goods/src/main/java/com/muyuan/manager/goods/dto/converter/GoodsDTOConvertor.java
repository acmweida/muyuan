package com.muyuan.manager.goods.dto.converter;

import com.muyuan.manager.goods.dto.GoodsDTO;
import com.muyuan.manager.goods.model.Goods;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoodsDTOConvertor {

    Goods toEntity(GoodsDTO goodsDTO);
}
