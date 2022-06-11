package com.muyuan.product.domains.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.domains.service.ProductDomainService;
import com.muyuan.product.domains.service.impl.ProductDomainServiceImpl;

public class ProductFactory {

    public static ProductDomainService createProductService(Product product) {
        return  new ProductDomainServiceImpl(product, ApplicationContextHandler.getContext().getBean(ProductRepo.class));
    }

}
