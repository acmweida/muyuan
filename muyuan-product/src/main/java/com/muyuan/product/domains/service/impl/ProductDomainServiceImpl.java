package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.JdbcValueConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.model.Sku;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.domains.service.ProductDomainService;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductServiceImpl
 * Description 商品域服务实现
 * @Author 2456910384
 * @Date 2021/10/13 15:28
 * @Version 1.0
 */
public class ProductDomainServiceImpl implements ProductDomainService {

    private Product product;

    private List<Sku> skus;

    private Brand brand;

    private ProductRepo productRepo;

    public ProductDomainServiceImpl(Product product, ProductRepo repo) {
        this.product = product;
    }

    public ProductDomainServiceImpl(Product product, List<Sku> skus, Brand brand, ProductRepo productRepo) {
        this.product = product;
        this.skus = skus;
        this.brand = brand;
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Long> addProduct() {



        return Optional.empty();
    }

    @Override
    public List<Product> queryProductsByShopInfo(ShopProductDTO shopProductDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(Product.class)
                .eq("delete", JdbcValueConst.DELETE_FALSE)
                .eq("id",shopProductDTO.getShopId());

        if (!ObjectUtils.isEmpty(shopProductDTO.getCategoryId())) {
            sqlBuilder.eq("categoryId",shopProductDTO.getCategoryId());
        }

        List<Product> products = productRepo.queryList(sqlBuilder.build());

        return products;
    }
}
