package com.muyuan.product.domains.service.impl;

import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.repo.CategoryAttributeRepo;
import com.muyuan.product.domains.service.CategoryAttributeDomainService;
import com.muyuan.product.infrastructure.persistence.mapper.CategoryAttributeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类属性Service业务层处理
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Service
@AllArgsConstructor
@Slf4j
public class CategoryAttributeDomainServiceImpl implements CategoryAttributeDomainService
{
    private CategoryAttributeMapper categoryAttributeMapper;

    private CategoryAttributeRepo categoryAttributeRepo;

    /**
     * 查询商品分类属性
     * 
     * @param id 商品分类属性主键
     * @return 商品分类属性
     */
    @Override
    public CategoryAttribute selectCategoryAttributeById(Long id)
    {
        return categoryAttributeMapper.selectCategoryAttributeById(id);
    }

    /**
     * 查询商品分类属性列表
     * 
     * @param categoryAttribute 商品分类属性
     * @return 商品分类属性
     */
    @Override
    public List<CategoryAttribute> selectCategoryAttributeList(CategoryAttribute categoryAttribute)
    {
        return categoryAttributeMapper.selectCategoryAttributeList(categoryAttribute);
    }

    /**
     * 新增商品分类属性
     * 
     * @param categoryAttribute 商品分类属性
     * @return 结果
     */
    @Override
    public void add(CategoryAttributeDTO categoryAttribute)
    {
        CategoryAttribute attribute = categoryAttribute.convert();
        attribute.init();
        attribute.save(categoryAttributeRepo);

    }

    /**
     * 修改商品分类属性
     * 
     * @param categoryAttribute 商品分类属性
     * @return 结果
     */
    @Override
    public int updateCategoryAttribute(CategoryAttribute categoryAttribute)
    {
        categoryAttribute.setUpdateTime(DateTime.now().toDate());
        return categoryAttributeMapper.updateCategoryAttribute(categoryAttribute);
    }

    /**
     * 批量删除商品分类属性
     * 
     * @param ids 需要删除的商品分类属性主键
     * @return 结果
     */
    @Override
    public int deleteCategoryAttributeByIds(Long[] ids)
    {
        return categoryAttributeMapper.deleteCategoryAttributeByIds(ids);
    }

    /**
     * 删除商品分类属性信息
     * 
     * @param id 商品分类属性主键
     * @return 结果
     */
    @Override
    public int deleteCategoryAttributeById(Long id)
    {
        return categoryAttributeMapper.deleteCategoryAttributeById(id);
    }
}
