package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.domains.repo.CategoryRepo;
import com.muyuan.goods.domains.service.CategoryService;
import com.muyuan.goods.face.dto.CategoryCommand;
import com.muyuan.goods.face.dto.CategoryQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ${author}
 * @ClassName CategoryDomainServiceImpl
 * Description 权限
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    @Override
    public Page<Category> list(CategoryQueryCommand commend) {
        return categoryRepo.select(commend);
    }

    @Override
    public List<Category> listByBrandId(Long... brandIds) {
        if (ObjectUtils.isEmpty(brandIds)) {
            return GlobalConst.EMPTY_LIST;
        }
        return categoryRepo.selectByBrandId(brandIds);
    }

    @Override
    public String checkUnique(Category.Identify identify) {
        Long id = null == identify.getId() ? null : identify.getId();
        Category category = categoryRepo.selectCategory(identify);
        if (null != category && !id.equals(category.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean addCategory(CategoryCommand command) {
        Category category = new Category();

        category.setParentId(command.getParentId());
        category.setName(command.getName());
        category.setLevel(command.getLevel());
        category.setCreateTime(command.getCreateTime());
        category.setUpdateTime(command.getUpdateTime());
        category.setCode(command.getCode());
        category.setAncestors(command.getAncestors());
        category.setLogo(command.getLogo());
        category.setProductCount(command.getProductCount());
        category.setStatus(command.getStatus());
        category.setOrderNum(command.getOrderNum());
        category.setCreateBy(command.getCreateBy());
        category.setCreator(command.getCreator());
        category.setUpdateBy(command.getUpdateBy());
        category.setUpdater(command.getUpdater());
        category.setLeaf(command.getLeaf());
        category.setSubCount(command.getSubCount());

        return categoryRepo.addCategory(category);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return categoryRepo.selectCategory(id_);
                });
    }

    @Override
    public boolean updateCategory(CategoryCommand command) {
        Category category = new Category();

        category.setId(command.getId());
        category.setParentId(command.getParentId());
        category.setName(command.getName());
        category.setLevel(command.getLevel());
        category.setCreateTime(command.getCreateTime());
        category.setUpdateTime(command.getUpdateTime());
        category.setCode(command.getCode());
        category.setAncestors(command.getAncestors());
        category.setLogo(command.getLogo());
        category.setProductCount(command.getProductCount());
        category.setStatus(command.getStatus());
        category.setOrderNum(command.getOrderNum());
        category.setCreateBy(command.getCreateBy());
        category.setCreator(command.getCreator());
        category.setUpdateBy(command.getUpdateBy());
        category.setUpdater(command.getUpdater());
        category.setLeaf(command.getLeaf());
        category.setSubCount(command.getSubCount());

        Category old = categoryRepo.updateCategory(category);
        if (ObjectUtils.isNotEmpty(old)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCategoryById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList(Arrays.asList(ids));

        List<Category> olds = categoryRepo.deleteBy(removeIds.toArray(new Long[0]));

        return !olds.isEmpty();
    }


}
