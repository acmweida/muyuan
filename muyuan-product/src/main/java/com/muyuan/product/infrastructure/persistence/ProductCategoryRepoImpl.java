package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
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
                .like("name",productCategoryDTO.getName())
                .eq("code",productCategoryDTO.getCode())
                .eq("parentId",productCategoryDTO.getParentId())
                .notEq("status","2")
                .build());
    }

    @Override
    public ProductCategory selectOne(ProductCategory productCategory) {
        return productCategoryMapper.selectOne(new SqlBuilder(ProductCategory.class)
                .eq("id",productCategory.getId())
                .eq("name", productCategory.getName())
                .eq("parentId",productCategory.getParentId())
                .build());
    }

    @Override
    public void insert(ProductCategory product) {
        productCategoryMapper.insertAuto(product);
    }

    @Override
    public void update(ProductCategory productCategory) {
        productCategoryMapper.updateBy(productCategory,"id");
    }

    @Override
    public int count(ProductCategoryDTO productCategoryDTO) {
        return productCategoryMapper.count(new SqlBuilder(ProductCategory.class)
                .eq("level",productCategoryDTO.getLevel())
                .eq("parentId",productCategoryDTO.getParentId())
                .build());
    }

    @Override
    public void delete(String[] ids) {
        productCategoryMapper.update(new SqlBuilder()
                .set("status","2")
                .set("updater", SecurityUtils.getUsername())
                .set("updateBy", SecurityUtils.getUserId())
                .in("id",ids)
                .build());
    }


}
