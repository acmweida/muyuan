package com.muyuan.goods.face.dto.mapper;

import com.muyuan.goods.api.dto.FeatureDTO;
import com.muyuan.goods.api.dto.FeatureQueryRequest;
import com.muyuan.goods.api.dto.FeatureRequest;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.face.dto.FeatureCommand;
import com.muyuan.goods.face.dto.FeatureQueryCommand;
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
public interface FeatureMapper {

    FeatureQueryCommand toCommand(FeatureQueryRequest request);

    FeatureCommand toCommand(FeatureRequest request);

    List<FeatureDTO> toDTO(List<Feature> feature);

    FeatureDTO toDTO(Feature feature);

}
