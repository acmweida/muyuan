package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
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
import java.util.Optional;

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
        productCategory.init();
        if (ObjectUtils.isNotEmpty(productCategory.getParentId())) {
            productCategoryRepo.insert(productCategory);
            ProductCategory parent = productCategoryRepo.selectOne(
                    ProductCategory.builder().id(productCategoryDTO.getParentId()).build());

            productCategory.linkParent(parent);
            productCategoryRepo.update(productCategory);
            productCategoryRepo.update(parent);

        } else {
            int rootCount = productCategoryRepo.count(ProductCategoryDTO.builder().level(1).build());
            productCategory.initRoot(rootCount+1);
            productCategoryRepo.insert(productCategory);
        }

    }

    @Override
    public void update(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryDTO.convert();
        productCategoryRepo.update(productCategory);
    }

    @Override
    public String checkUnique(ProductCategory productCategory) {
        Long id = null == productCategory.getId() ? 0 : productCategory.getId();
        productCategory = productCategoryRepo.selectOne(
                ProductCategory.builder().name(productCategory.getName())
                        .parentId(productCategory.getParentId()).build());
        if (null != productCategory && !id.equals(productCategory.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public Optional<ProductCategory> get(String id) {
        ProductCategory productCategory = productCategoryRepo.selectOne(ProductCategory.builder()
                .id(Long.valueOf(id))
                .build());

        return Optional.ofNullable(productCategory);
    }
}
