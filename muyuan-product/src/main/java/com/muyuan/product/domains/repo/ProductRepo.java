package com.muyuan.product.domains.repo;

import com.muyuan.product.domains.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepo {

    List<Product> queryList(Map param);

    boolean insert(Product product);
}
