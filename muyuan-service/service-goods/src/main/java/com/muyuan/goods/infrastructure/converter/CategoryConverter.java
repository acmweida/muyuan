package com.muyuan.goods.infrastructure.converter;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.infrastructure.dataobject.CategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(target = "leaf",expression = "java(CategoryConverter.map(categoryDO.getLeaf()))")
    Category to(CategoryDO categoryDO);

    static Boolean map(String lead) {
        return GlobalConst.TRUE.equals(lead);
    }

    static String map(Boolean lead) {
        return lead ? "0" : "1";
    }

    List<Category> to(List<CategoryDO> categoryDOS);

    @Mapping(target = "leaf",expression = "java(CategoryConverter.map(category.getLeaf()))")
    CategoryDO to(Category category);

}
