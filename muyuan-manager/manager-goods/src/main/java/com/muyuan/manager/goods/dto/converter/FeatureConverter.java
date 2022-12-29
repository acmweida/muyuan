package com.muyuan.manager.goods.dto.converter;

import com.muyuan.goods.api.dto.FeatureRequest;
import com.muyuan.manager.goods.dto.FeatureParams;
import org.mapstruct.Mapper;

/**
 * @ClassName FeatureConverter
 * Description
 * @Author  ${author}
 * @Date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface FeatureConverter {

    FeatureRequest to(FeatureParams params);


}
