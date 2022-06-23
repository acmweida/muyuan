package com.muyuan.product.domains.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.service.GoodsDomainService;
import com.muyuan.product.domains.service.impl.GoodsDomainServiceImpl;

public class GoodsFactory {

    public static GoodsDomainService createProductService(Goods goods) {
        return  new GoodsDomainServiceImpl(goods, ApplicationContextHandler.getContext().getBean(GoodsRepo.class));
    }

}
