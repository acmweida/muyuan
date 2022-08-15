package com.muyuan.manager.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.product.infrastructure.persistence.mapper.BrandCategoryMapper;
import com.muyuan.manager.product.infrastructure.persistence.mapper.CategoryMapper;
import com.muyuan.manager.product.domains.dto.CategoryDTO;
import com.muyuan.manager.product.domains.model.BrandCategory;
import com.muyuan.manager.product.domains.model.Category;
import com.muyuan.manager.product.domains.repo.CategoryRepo;
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
public class CategoryRepoImpl implements CategoryRepo {

    private CategoryMapper categoryMapper;

    private BrandCategoryMapper brandCategoryMapper;

    @Override
    public List<Category> list(CategoryDTO categoryDTO) {
        return categoryMapper.selectList(new SqlBuilder(Category.class)
                .like(NAME, categoryDTO.getName())
                .eq(CODE, categoryDTO.getCode())
                .eq(PARENT_ID, categoryDTO.getParentId())
                .in(PARENT_ID, categoryDTO.getParentIds())
                .in(ID,  categoryDTO.getIds())
                .eq(LEAF, categoryDTO.getLeaf())
                .eq(LEVEL, categoryDTO.getLevel())
                .in(STATUS, STATUS_OK)
                .build());
    }

    public List<Category> list(CategoryDTO categoryDTO, String... column) {
        return categoryMapper.selectList(new SqlBuilder(Category.class)
                .select(column)
                .eq(CODE, categoryDTO.getCode())
                .eq(PARENT_ID, categoryDTO.getParentId())
                .in(PARENT_ID, (Object) categoryDTO.getParentIds())
                .in(STATUS, STATUS_OK)
                .build());
    }

    @Override
    public Category selectOne(Category category) {
        return categoryMapper.selectOne(new SqlBuilder(Category.class)
                .eq(ID, category.getId())
                .eq(NAME, category.getName())
                .eq(PARENT_ID, category.getParentId())
                .eq(CODE, category.getCode())
                .eq(LEVEL, category.getLevel())
                .in(STATUS, STATUS_OK)
                .build());
    }

    @Override
    public void insert(Category product) {
        categoryMapper.insertAuto(product);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateBy(category, ID);

    }

    @Override
    public void update(Category category, String... column) {
        categoryMapper.updateColumnBy(category, column, ID);
    }

    @Override
    public int count(CategoryDTO categoryDTO) {
        return categoryMapper.count(new SqlBuilder(Category.class)
                .eq(LEVEL, categoryDTO.getLevel())
                .eq(PARENT_ID, categoryDTO.getParentId())
                .in(STATUS, STATUS_OK)
                .build());
    }

    @Override
    public int countAll(CategoryDTO categoryDTO) {
        return categoryMapper.count(new SqlBuilder(Category.class)
                .eq(LEVEL, categoryDTO.getLevel())
                .eq(PARENT_ID, categoryDTO.getParentId())
                .build());
    }

    @Override
    public void delete(Long[] ids) {
        categoryMapper.update(new SqlBuilder()
                .set(STATUS, STATUS_DELETE)
                .set(UPDATER, SecurityUtils.getUsername())
                .set(UPDATE_BY, SecurityUtils.getUserId())
                .in(ID, ids)
                .build());
    }

    @Override
    public List<BrandCategory> selectBrand(Long... categoryCode) {
        return brandCategoryMapper.selectList(new SqlBuilder(BrandCategory.class)
                .in(CATEGORY_CODE, categoryCode)
                .build());
    }

}
