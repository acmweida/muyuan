package com.muyuan.goods.face.dto.converter;

import com.muyuan.goods.api.dto.GoodsDTO;
import com.muyuan.goods.api.dto.GoodsQueryRequest;
import com.muyuan.goods.domains.model.entity.Goods;
import com.muyuan.goods.face.dto.GoodsQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName GoodsMapper
 * Description 商品请求DTO转换
 * @Author 2456910384
 * @Date 2022/8/26 14:52
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface GoodsDTOConverter {

    GoodsQueryCommand toCommand(GoodsQueryRequest request);

    @Mapping(target = "id",source = "id.value")
    GoodsDTO toDTO(Goods goods);

    List<GoodsDTO> toDTO(List<Goods> goods);

}
