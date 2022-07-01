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

}
