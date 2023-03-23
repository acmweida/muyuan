package com.muyuan.goods.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.muyuan.common.bean.Page;
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
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<CategoryDO>()
                .eq(CategoryDO::getParentId,command.getParentId())
                .eq(CategoryDO::getName,command.getName())
                .eq(CategoryDO::getLevel,command.getLevel())
                .eq(CategoryDO::getCode,command.getCode())
                .eq(CategoryDO::getStatus,command.getStatus())
                .eq(CategoryDO::getLeaf,command.getLeaf())
                .in(CategoryDO::getStatus, STATUS_OK)
;

        Page<Category> page = Page.<Category>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<CategoryDO> list = command.enablePage() ?
                categoryMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                categoryMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public List<Category> selectByBrandId(Long... brandIds) {
        return converter.to(categoryMapper.selectByBrandId(brandIds));
    }

    @Override
    public Category selectCategory(Long id) {
        CategoryDO categoryDO = categoryMapper.selectOne(new LambdaQueryWrapper<CategoryDO>()
                .eq(CategoryDO::getId, id));
        return converter.to(categoryDO);
    }

    @Override
    public Category selectCategoryByCode(Long code) {
        CategoryDO categoryDO = categoryMapper.selectOne(new LambdaQueryWrapper<CategoryDO>()
                .eq(CategoryDO::getCode, code));
        return converter.to(categoryDO);
    }

    @Override
    public Category selectCategory(Category.Identify identify) {
        CategoryDO categoryDO = categoryMapper.selectOne(new LambdaQueryWrapper<CategoryDO>().select(CategoryDO::getId)
                .eq(ObjectUtils.isNotEmpty(identify.getId()),CategoryDO::getId, identify.getId())
                .eq(CategoryDO::getParentId,identify.getParentId())
                .eq(CategoryDO::getName,identify.getName()));

        return converter.to(categoryDO);
    }

    @Override
    public boolean addCategory(Category category) {
        CategoryDO to = converter.to(category);
        Integer count = categoryMapper.insert(to);
        category.setId(to.getId());
        return count > 0;
    }

    @Override
    public int count(CategoryQueryCommand command) {
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<CategoryDO>()
                .eq(CategoryDO::getParentId,command.getParentId())
                .eq(CategoryDO::getName,command.getName())
                .eq(CategoryDO::getLevel,command.getLevel())
                .eq(CategoryDO::getCode,command.getCode())
                .eq(CategoryDO::getAncestors,command.getAncestors())
                .eq(CategoryDO::getStatus,command.getStatus())
                .eq(CategoryDO::getLeaf,command.getLeaf())
                .in(CategoryDO::getStatus, STATUS_OK);


        return categoryMapper.selectCount(wrapper).intValue();
    }

    @Override
    public Category updateCategory(Category category) {
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<CategoryDO>()
                .eq(CategoryDO::getId, category.getId());

        CategoryDO categoryDO = categoryMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(categoryDO)) {
            categoryMapper.updateById(converter.to(category));
        }

        return converter.to(categoryDO);
    }

    @Override
    public Category updateCategoryAncestors(Category category) {
        UpdateWrapper<CategoryDO> wrapper = new UpdateWrapper<CategoryDO>()
                .eq(ID,category.getId())
                .set(ANCESTORS,category.getAncestors());
        categoryMapper.update(null,wrapper);
        return category;
    }

    @Override
    public List<Category> deleteBy(Long... ids) {
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<CategoryDO>()
                .in(CategoryDO::getId, ids);
        List<CategoryDO> categorys = categoryMapper.selectList(wrapper);

        categoryMapper.delete(wrapper);

        return converter.to(categorys);
    }

}
