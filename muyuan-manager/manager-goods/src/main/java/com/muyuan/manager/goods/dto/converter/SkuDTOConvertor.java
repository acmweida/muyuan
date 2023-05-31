package com.muyuan.manager.goods.dto.converter;

import com.muyuan.manager.goods.dto.SkuDTO;
import com.muyuan.manager.goods.model.Sku;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkuDTOConvertor {

    Sku toEntity(SkuDTO skuDTO);
}
