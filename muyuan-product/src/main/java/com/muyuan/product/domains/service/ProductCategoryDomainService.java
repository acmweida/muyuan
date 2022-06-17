package com.muyuan.product.domains.service;

import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.interfaces.dto.ProductCategoryDTO;

import java.util.List;

/**
 * @ClassName ProductCategoryDomainService 接口
 * Description 产品分类域服务
 * @Author 2456910384
 * @Date 2022/6/9 15:41
 * @Version 1.0
 */
public interface ProductCategoryDomainService {

    /**
     * 分类列表查询
     * @param productCategoryDTO
     * @return
     */
    List<ProductCategory> list(ProductCategoryDTO productCategoryDTO);

    /**
     * 新增分类
     * @param productCategoryDTO
     */
    void add(ProductCategoryDTO productCategoryDTO);

}