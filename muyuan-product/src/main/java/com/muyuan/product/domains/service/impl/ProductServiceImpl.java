package com.muyuan.product.domains.service.impl;

import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.model.Sku;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.domains.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductServiceImpl
 * Description 商品域服务实现
 * @Author 2456910384
 * @Date 2021/10/13 15:28
 * @Version 1.0
 */
public class ProductServiceImpl implements ProductService {

    private Product product;

    private List<Sku> skus;

    private Brand brand;

    private ProductRepo productRepo;

    public ProductServiceImpl(Product product,ProductRepo repo) {
        this.product = product;
    }

    public ProductServiceImpl(Product product, List<Sku> skus, Brand brand, ProductRepo productRepo) {
        this.product = product;
        this.skus = skus;
        this.brand = brand;
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Long> addProduct() {



        return Optional.empty();
    }
}
