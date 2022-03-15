package com.muyuan.product.domains.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.domains.service.ProductService;
import com.muyuan.product.domains.service.impl.ProductServiceImpl;

public class ProductFactory {

    public static ProductService createProductService(Product product) {
        return  new ProductServiceImpl(product, ApplicationContextHandler.getContext().getBean(ProductRepo.class));
    }

}
