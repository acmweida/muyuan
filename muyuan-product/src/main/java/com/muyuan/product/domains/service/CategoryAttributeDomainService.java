package com.muyuan.product.domains.service;

import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;

/**
 * 商品分类属性Service接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
public interface CategoryAttributeDomainService
{
    String CATEGORY_ATTRIBUTE_KEY_PREFIX = "category:attribute:";

    /**
     * 新增商品分类属性
     * 
     * @param categoryAttribute 商品分类属性
     * @return 结果
     */
    void add(CategoryAttributeDTO categoryAttribute);

    /**
     * 修改商品分类属性
     * 
     * @param categoryAttribute 商品分类属性
     * @return 结果
     */
    void update(CategoryAttributeDTO categoryAttribute);

    /**
     * 批量删除商品分类属性
     * 
     * @param ids 需要删除的商品分类属性主键集合
     * @return 结果
     */
    void delete(String[] ids);

    /**
     * 唯一性查询
     * @param categoryAttribute
     * @return
     */
    String checkUnique(CategoryAttribute categoryAttribute);


}
