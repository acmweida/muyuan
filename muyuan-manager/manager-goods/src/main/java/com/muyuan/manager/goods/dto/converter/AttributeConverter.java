package com.muyuan.manager.goods.dto.converter;

import com.muyuan.goods.api.dto.AttributeRequest;
import com.muyuan.manager.goods.dto.AttributeParams;
import com.muyuan.manager.goods.dto.BrandParams;
import org.mapstruct.Mapper;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface AttributeConverter {

    AttributeRequest to(AttributeParams params);

}
