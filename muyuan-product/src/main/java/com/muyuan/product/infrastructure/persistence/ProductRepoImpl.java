package com.muyuan.product.infrastructure.persistence;

import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.repo.ProductRepo;
import com.muyuan.product.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductRepoImpl implements ProductRepo {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> queryList(Map param) {
        return productMapper.selectList(param);
    }

    @Override
    public boolean insert(Product product) {
        return productMapper.insert(product) > 0 ;
    }
}
