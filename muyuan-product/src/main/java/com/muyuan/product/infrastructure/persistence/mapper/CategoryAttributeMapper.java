package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;

import java.util.List;

/**
 * 商品分类属性Mapper接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
public interface CategoryAttributeMapper extends ProductBaseMapper<CategoryAttribute> {

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
    int insertCategoryAttribute(CategoryAttribute categoryAttribute);

    /**
     * 修改商品分类属性
     *
     * @param categoryAttribute 商品分类属性
     * @return 结果
     */
    int updateCategoryAttribute(CategoryAttribute categoryAttribute);

    /**
     * 删除商品分类属性
     *
     * @param id 商品分类属性主键
     * @return 结果
     */
    int deleteCategoryAttributeById(Long id);

    /**
     * 批量删除商品分类属性
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCategoryAttributeByIds(Long[] ids);
}
