package com.muyuan.goods.infrastructure.converter;

import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.infrastructure.dataobject.CategoryDO;
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
public interface CategoryConverter {

    Category to(CategoryDO categoryDO);

    List<Category> to(List<CategoryDO> categoryDOS);

    CategoryDO to(Category category);

}
