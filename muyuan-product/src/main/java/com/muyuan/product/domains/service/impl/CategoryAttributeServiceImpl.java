package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.repo.CategoryAttributeRepo;
import com.muyuan.product.domains.service.CategoryAttributeService;
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
public class CategoryAttributeServiceImpl implements CategoryAttributeService
{

    private CategoryAttributeRepo categoryAttributeRepo;

    private RedisCacheService redisCacheService;

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
        redisCacheService.del(CATEGORY_ATTRIBUTE_KEY_PREFIX+categoryAttribute.getCategoryCode());
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
        redisCacheService.del(CATEGORY_ATTRIBUTE_KEY_PREFIX+ RedisConst.ALL_PLACE_HOLDER);
    }

}
