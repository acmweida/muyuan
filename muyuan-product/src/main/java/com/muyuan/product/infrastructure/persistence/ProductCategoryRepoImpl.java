package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.domains.repo.ProductCategoryRepo;
import com.muyuan.product.infrastructure.persistence.mapper.ProductCategoryMapper;
import com.muyuan.product.interfaces.dto.ProductCategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ProductCategoryRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/6/10 11:48
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class ProductCategoryRepoImpl implements ProductCategoryRepo {

    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> list(ProductCategoryDTO productCategoryDTO) {
        return productCategoryMapper.selectList(new SqlBuilder(ProductCategory.class)
                .build());
    }
}
