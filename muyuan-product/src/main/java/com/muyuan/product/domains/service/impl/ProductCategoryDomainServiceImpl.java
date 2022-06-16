package com.muyuan.product.domains.service.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.domains.repo.ProductCategoryRepo;
import com.muyuan.product.domains.service.ProductCategoryDomainService;
import com.muyuan.product.interfaces.dto.ProductCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ProductCategoryDomainServiceImpl
 * Description 商品分类
 * @Author 2456910384
 * @Date 2022/6/10 8:57
 * @Version 1.0
 */
@Service
@Slf4j
@AllArgsConstructor
public class ProductCategoryDomainServiceImpl implements ProductCategoryDomainService {

    private ProductCategoryRepo productCategoryRepo;

    @Override
    public List<ProductCategory> list(ProductCategoryDTO productCategoryDTO) {
        return productCategoryRepo.list(productCategoryDTO);
    }

    @Override
    @Transactional
    public void add(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryDTO.convert();
        if (ObjectUtils.isNotEmpty(productCategory.getParentId())) {
            productCategoryRepo.insert(productCategory);
            ProductCategory parent = productCategoryRepo.selectOne(new SqlBuilder(ProductCategory.class)
                    .eq("id",productCategory.getParentId())
                    .build());

            productCategory.linkParent(parent);
            productCategoryRepo.update(productCategory);

        } else {
            productCategory.clearParentId();
            productCategoryRepo.insert(productCategory);

        }

    }
}
