package com.muyuan.product.domains.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.service.GoodsDomainService;
import com.muyuan.product.domains.service.impl.GoodsDomainServiceImpl;
import com.muyuan.product.domains.vo.CategoryAttributeVO;
import com.muyuan.product.domains.vo.GoodsCategoryVO;
import com.muyuan.product.infrastructure.common.constant.Const;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.*;

public class GoodsFactory {

    public static GoodsDomainService createProductService(Goods goods) {
        return new GoodsDomainServiceImpl(goods, ApplicationContextHandler.getContext().getBean(GoodsRepo.class));
    }

    public static GoodsCategoryVO convert(GoodsCategory category) {
        Assert.notNull(category, "category must not be null");
        GoodsCategoryVO categoryVO = new GoodsCategoryVO();
        BeanUtils.copyProperties(category, categoryVO);

        List<CategoryAttributeVO> attributeVOS = new LinkedList<>();
        CategoryAttributeVO categoryAttributeVO;
        if (ObjectUtils.isNotEmpty(category.getAttributes())) {
            Iterator<CategoryAttribute> it = category.getAttributes().iterator();

            while (it.hasNext()) {
                CategoryAttribute attribute = it.next();
                categoryAttributeVO = new CategoryAttributeVO();
                BeanUtils.copyProperties(attribute, categoryAttributeVO);
                categoryAttributeVO.setCommon(attribute.isType(Const.CATEGORY_ATTRIBUTE_COMMON));
                categoryAttributeVO.setSale(attribute.isType(Const.CATEGORY_ATTRIBUTE_SALE));
                categoryAttributeVO.setKey(attribute.isType(Const.CATEGORY_ATTRIBUTE_KEY));
                categoryAttributeVO.setNormal(attribute.isType(Const.CATEGORY_ATTRIBUTE_NORMAL));
                attributeVOS.add(categoryAttributeVO);
            }

        }
        return categoryVO;
    }

}
