package com.muyuan.goods.infrastructure.repo.converter;

import com.muyuan.goods.domains.model.entity.AttributeValue;
import com.muyuan.goods.infrastructure.repo.dataobject.AttributeValueDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface AttributeValueConverter {

    AttributeValue to(AttributeValueDO attributeValueDO);

    List<AttributeValue> to(List<AttributeValueDO> attributeValueDOS);

    AttributeValueDO toDO(AttributeValue attributeValue);

    List<AttributeValueDO> toDO(List<AttributeValue> attributeValues);

}
