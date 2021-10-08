package com.muyuan.product.domains.query.impl;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.query.ProductQuery;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductQueryImpl implements ProductQuery {

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> queryProductsByShopInfo(ShopProductDTO shopProductDTO) {
        List<Product> products = productRepo.queryList(new SqlBuilder(Product.class).build());

        return products;
    }
}
