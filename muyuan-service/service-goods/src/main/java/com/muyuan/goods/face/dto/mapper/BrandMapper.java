package com.muyuan.goods.face.dto.mapper;

import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName GoodsMapper
 * Description 商品请求DTO转换
 * @Author 2456910384
 * @Date 2022/8/26 14:52
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandQueryCommand toCommand(BrandQueryRequest request);

    BrandDTO toDTO(Brand goods);

    List<BrandDTO> toDTO(List<Brand> goods);



}
