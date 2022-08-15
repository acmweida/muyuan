package com.muyuan.manager.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.manager.product.domains.dto.AttributeDTO;
import com.muyuan.manager.product.domains.model.Attribute;
import com.muyuan.manager.product.domains.repo.AttributeRepo;
import com.muyuan.manager.product.domains.service.AttributeService;
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
public class AttributeServiceImpl implements AttributeService
{

    private AttributeRepo attributeRepo;

    private RedisCacheService redisCacheService;

    @Override
    public String checkUnique(Attribute attribute) {
        Long id = null == attribute.getId() ? 0 : attribute.getId();
        attribute = attributeRepo.selectOne(attribute);
        if (null != attribute && !attribute.getId().equals(id)) {
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
    public void add(AttributeDTO categoryAttribute)
    {
        Attribute attribute = categoryAttribute.convert();
        attribute.init();
        attribute.save(attributeRepo);
    }

    /**
     * 修改商品分类属性
     * 
     * @param categoryAttribute 商品分类属性
     * @return 结果
     */
    @Override
    public void update(AttributeDTO categoryAttribute)
    {
        categoryAttribute.convert().save(attributeRepo);
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
        attributeRepo.delete(ids);
        redisCacheService.del(CATEGORY_ATTRIBUTE_KEY_PREFIX+ RedisConst.ALL_PLACE_HOLDER);
    }

}
