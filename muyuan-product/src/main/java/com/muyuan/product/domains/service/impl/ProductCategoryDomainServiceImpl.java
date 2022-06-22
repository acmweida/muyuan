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
            ProductCategory parent = productCategoryRepo.selectOne(
                    ProductCategory.builder().id(productCategoryDTO.getParentId()).build());
            if (ObjectUtils.isEmpty(parent)) {
                addRootCategory(productCategory);
            } else {
                addSubNodeCategory(productCategory,parent);
            }
        } else {
            addRootCategory(productCategory);
        }
    }

    private void  addRootCategory(ProductCategory productCategory) {
        int rootCount = productCategoryRepo.count(ProductCategoryDTO.builder().level(1).build());
        productCategory.initRoot(rootCount);
        productCategory.save(productCategoryRepo);
        productCategory.setAncestors(String.valueOf(productCategory.getId()));
        productCategory.save(productCategoryRepo);
    }

    private void addSubNodeCategory(ProductCategory productCategory,ProductCategory parent) {
        productCategory.save(productCategoryRepo);
        // 查询兄弟节点数量 用于生成Code
        int count = productCategoryRepo.count(ProductCategoryDTO.builder()
                .level(parent.getLevel() + 1)
                .parentId(parent.getId())
                .build());
        productCategory.linkParent(parent,count);
        productCategory.save(productCategoryRepo);
        parent.save(productCategoryRepo);
    }


    @Override
    public void update(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryDTO.convert();
        productCategory.save(productCategoryRepo);
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

    @Override
    public void delete(String[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        productCategoryRepo.delete(ids);
    }
}
