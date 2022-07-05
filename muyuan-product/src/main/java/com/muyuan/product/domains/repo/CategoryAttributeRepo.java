package com.muyuan.product.domains.repo;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;

import java.util.List;

/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */

public interface CategoryAttributeRepo {


    List<CategoryAttribute> select(CategoryAttributeDTO categoryAttributeDTO, Page page);

    void insert(CategoryAttribute categoryAttribute);

    void update(CategoryAttribute categoryAttribute);

    void delete(String... ids);

    CategoryAttribute selectOne(CategoryAttribute categoryAttribute);

}
