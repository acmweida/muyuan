package com.muyuan.goods.face.dto.transfor;

import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.goods.api.dto.CategoryQueryRequest;
import com.muyuan.goods.api.dto.CategoryRequest;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.face.dto.CategoryCommand;
import com.muyuan.goods.face.dto.CategoryQueryCommand;
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
public interface CategoryTransfer {

    CategoryQueryCommand toCommand(CategoryQueryRequest request);

    CategoryCommand toCommand(CategoryRequest request);

    List<CategoryDTO> toDTO(List<Category> category);

    CategoryDTO toDTO(Category category);

}
