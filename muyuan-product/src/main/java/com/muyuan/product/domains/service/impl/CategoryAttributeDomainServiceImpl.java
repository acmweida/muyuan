package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.repo.CategoryAttributeRepo;
import com.muyuan.product.domains.service.CategoryAttributeDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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

    private CategoryAttributeRepo categoryAttributeRepo;


    @Override
    public String checkUnique(CategoryAttribute categoryAttribute) {
        Long id = null == categoryAttribute.getId() ? 0 : categoryAttribute.getId();
        categoryAttribute = categoryAttributeRepo.selectOne(categoryAttribute);
        if (null != categoryAttribute && !categoryAttribute.getId().equals(id)) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
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
    public void update(CategoryAttributeDTO categoryAttribute)
    {
        categoryAttribute.convert().save(categoryAttributeRepo);
    }

    /**
     * 批量删除商品分类属性
     * 
     * @param ids 需要删除的商品分类属性主键
     * @return 结果
     */
    @Override
    public void delete(String[] ids)
    {
        categoryAttributeRepo.delete(ids);
    }

}
