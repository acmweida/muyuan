package com.muyuan.manager.goods.dto.converter;

import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.goods.api.dto.CategoryRequest;
import com.muyuan.manager.goods.dto.CategoryParams;
import com.muyuan.manager.goods.dto.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter {

    CategoryRequest to(CategoryParams params);

    @Mapping(target = "hasChildren",expression = "java(!categoryDTO.getLeaf())")
    CategoryVO toVO(CategoryDTO categoryDTO);

    List<CategoryVO> toVO(List<CategoryDTO> categoryDTO);

}
