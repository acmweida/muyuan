package com.muyuan.product.domains.repo;

import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.interfaces.dto.ProductCategoryDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProductCategoryRepo 接口
 * Description 商品分类 Repository
 * @Author 2456910384
 * @Date 2022/6/10 11:37
 * @Version 1.0
 */
public interface ProductCategoryRepo {

    List<ProductCategory> list(ProductCategoryDTO productCategoryDTO);

    void add(ProductCategory productCategory);

    ProductCategory selectOne(Map params);

    void insert(ProductCategory productCategory);

    void update(ProductCategory productCategory);

}
