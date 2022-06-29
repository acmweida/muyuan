package com.muyuan.product.domains.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.service.GoodsDomainService;
import com.muyuan.product.domains.service.impl.GoodsDomainServiceImpl;
import com.muyuan.product.domains.vo.GoodsCategoryVO;
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

        List<CategoryAttribute> common = new ArrayList<>();
        List<CategoryAttribute> key = new ArrayList<>();
        List<CategoryAttribute> sale = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(category.getAttributes())) {
            List<CategoryAttribute> attributes = new LinkedList<>(category.getAttributes());
            Iterator<CategoryAttribute> it = attributes.iterator();

            while (it.hasNext()) {
                CategoryAttribute attribute = it.next();
                Long type = attribute.getType();
                if (ObjectUtils.isNotEmpty(attribute)) {
                    if ((type & 1) > 0) {
                        common.add(attribute);
                    } else if ((type & 2) > 0) {
                        sale.add(attribute);
                    } else if ((type & 4) > 0) {
                        key.add(attribute);
                    }
                    it.remove();
                }
            }

            categoryVO.setCommonAttributes(common);
            categoryVO.setSaleAttribute(sale);
            categoryVO.setKeyAttributes(key);
            categoryVO.setNormalAttributes(attributes);
        }
        return categoryVO;
    }

}
