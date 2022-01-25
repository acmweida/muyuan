package com.muyuan.product.domains.query.impl;

import com.muyuan.common.constant.JdbcValueConst;
import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.query.ProductQuery;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ProductQueryImpl implements ProductQuery {

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> queryProductsByShopInfo(ShopProductDTO shopProductDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(Product.class)
                .eq("delete", JdbcValueConst.BOOL_FALSE)
                .eq("id",shopProductDTO.getShopId());

        if (!ObjectUtils.isEmpty(shopProductDTO.getCategoryId())) {
            sqlBuilder.eq("categoryId",shopProductDTO.getCategoryId());
        }

        List<Product> products = productRepo.queryList(sqlBuilder.build());

        return products;
    }
}
