package com.muyuan.store.shop.domains.convertor;

import com.muyuan.store.shop.domains.dto.ShopDTO;
import com.muyuan.store.shop.domains.model.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopDTOConvertor {

    Shop toEntity(ShopDTO shopDTO);
}
