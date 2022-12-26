package com.muyuan.goods.face.dto.mapper;

import com.muyuan.goods.api.dto.AttributeDTO;
import com.muyuan.goods.domains.model.entity.Attribute;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface AttributeMapper {


    List<AttributeDTO> toDTO(List<Attribute> category);

    AttributeDTO toDTO(Attribute category);

}
