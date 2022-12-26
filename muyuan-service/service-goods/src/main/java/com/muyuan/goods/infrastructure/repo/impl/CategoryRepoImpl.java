package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.domains.repo.CategoryRepo;
import com.muyuan.goods.face.dto.CategoryQueryCommand;
import com.muyuan.goods.infrastructure.converter.CategoryConverter;
import com.muyuan.goods.infrastructure.dataobject.CategoryDO;
import com.muyuan.goods.infrastructure.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.goods.infrastructure.mapper.CategoryMapper.*;

@Component
@AllArgsConstructor
public class CategoryRepoImpl implements CategoryRepo {

    private CategoryMapper categoryMapper;

    private CategoryConverter converter;

    @Override
    public Page<Category> select(CategoryQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(CategoryDO.class)
                .eq(PARENT_ID,command.getParentId())
                .eq(NAME,command.getName())
                .eq(LEVEL,command.getLevel())
                .eq(CODE,command.getCode())
                .eq(STATUS,command.getStatus())
                .eq(LEAF,command.getLeaf())
                .in(STATUS, STATUS_OK)
;

        Page<Category> page = Page.<Category>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<CategoryDO> list = categoryMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public List<Category> selectByBrandId(Long... brandIds) {
        return converter.to(categoryMapper.selectByBrandId(brandIds));
    }

    @Override
    public Category selectCategory(Long id) {
        CategoryDO categoryDO = categoryMapper.selectOne(new SqlBuilder(CategoryDO.class)
                .eq(ID, id)
                .build());
        return converter.to(categoryDO);
    }

    @Override
    public Category selectCategoryByCode(Long code) {
        CategoryDO categoryDO = categoryMapper.selectOne(new SqlBuilder(CategoryDO.class)
                .eq(CODE, code)
                .build());
        return converter.to(categoryDO);
    }

    @Override
    public Category selectCategory(Category.Identify identify) {
        CategoryDO categoryDO = categoryMapper.selectOne(new SqlBuilder(CategoryDO.class).select(ID)
                .eq(ID, identify.getId())
                .eq(PARENT_ID,identify.getParentId())
                .eq(NAME,identify.getName())
                .build());

        return converter.to(categoryDO);
    }

    @Override
    public boolean addCategory(Category category) {
        CategoryDO to = converter.to(category);
        Integer count = categoryMapper.insertAuto(to);
        category.setId(to.getId());
        return count > 0;
    }

    @Override
    public int count(CategoryQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(CategoryDO.class)
                .eq(PARENT_ID,command.getParentId())
                .eq(NAME,command.getName())
                .eq(LEVEL,command.getLevel())
                .eq(CODE,command.getCode())
                .eq(ANCESTORS,command.getAncestors())
                .eq(STATUS,command.getStatus())
                .eq(LEAF,command.getLeaf())
                .in(STATUS, STATUS_OK)
                ;


        return categoryMapper.count(sqlBuilder.build());
    }

    @Override
    public Category updateCategory(Category category) {
        SqlBuilder sqlBuilder = new SqlBuilder(CategoryDO.class)
                .eq(ID, category.getId());

        CategoryDO categoryDO = categoryMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(categoryDO)) {
            categoryMapper.updateBy(converter.to(category), ID);
        }

        return converter.to(categoryDO);
    }

    @Override
    public Category updateCategoryAncestors(Category category) {
        categoryMapper.updateColumnBy(converter.to(category),new String[]{ANCESTORS},ID);
        return category;
    }

    @Override
    public List<Category> deleteBy(Long... ids) {
        List<CategoryDO> categorys = categoryMapper.selectList(new SqlBuilder(CategoryDO.class)
                .in(ID, ids)
                .build());

        categoryMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(categorys);
    }

}
