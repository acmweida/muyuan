package com.muyuan.goods.face.dto.converter;

import com.muyuan.goods.api.dto.AttributeDTO;
import com.muyuan.goods.api.dto.AttributeQueryRequest;
import com.muyuan.goods.api.dto.AttributeRequest;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.face.dto.AttributeCommand;
import com.muyuan.goods.face.dto.AttributeQueryCommand;
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
public interface AttributeDTOConverter {

    AttributeQueryCommand toCommand(AttributeQueryRequest request);

    AttributeCommand toCommand(AttributeRequest request);

    List<AttributeDTO> toDTO(List<Attribute> attribute);

    AttributeDTO toDTO(Attribute attribute);

}
