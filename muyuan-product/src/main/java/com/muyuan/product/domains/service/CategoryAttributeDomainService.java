package com.muyuan.product.domains.service;

import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;

import java.util.List;

/**
 * 商品分类属性Service接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
public interface CategoryAttributeDomainService
{
    /**
     * 查询商品分类属性
     *
     * @param id 商品分类属性主键
     * @return 商品分类属性
     */
    CategoryAttribute selectCategoryAttributeById(Long id);

    /**
     * 查询商品分类属性列表
     *
     * @param categoryAttribute 商品分类属性
     * @return 商品分类属性集合
     */
    List<CategoryAttribute> selectCategoryAttributeList(CategoryAttribute categoryAttribute);

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
    int updateCategoryAttribute(CategoryAttribute categoryAttribute);

    /**
     * 批量删除商品分类属性
     * 
     * @param ids 需要删除的商品分类属性主键集合
     * @return 结果
     */
    int deleteCategoryAttributeByIds(Long[] ids);

    /**
     * 删除商品分类属性信息
     *
     * @param id 商品分类属性主键
     * @return 结果
     */
    int deleteCategoryAttributeById(Long id);
}
