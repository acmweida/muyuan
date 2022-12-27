package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.domains.repo.AttributeRepo;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.domains.repo.CategoryRepo;
import com.muyuan.goods.domains.service.CategoryService;
import com.muyuan.goods.face.dto.AttributeQueryCommand;
import com.muyuan.goods.face.dto.CategoryCommand;
import com.muyuan.goods.face.dto.CategoryQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private AttributeRepo attributeRepo;

    private BrandRepo brandRepo;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean addCategory(CategoryCommand command) {
        Category category = new Category();

        category.setParentId(command.getParentId());
        category.setName(command.getName());
        category.setLogo(command.getLogo());
        category.setStatus(command.getStatus());
        category.setOrderNum(command.getOrderNum());

        category.init(command.getOpt());

        if (ObjectUtils.isNotEmpty(category.getParentId())) {
            Category parent = categoryRepo.selectCategory(command.getParentId());
            if (ObjectUtils.isEmpty(parent)) {
                addRootCategory(category);
            } else {
                addSubNodeCategory(category, parent);
            }
        } else {
            addRootCategory(category);
        }

        return true;
    }

    private void addRootCategory(Category category) {
        int rootCount = categoryRepo.count(CategoryQueryCommand.builder().level(1).build());
        category.initRoot(rootCount);
        categoryRepo.addCategory(category);
        category.setAncestors(String.valueOf(category.getId()));
        categoryRepo.updateCategoryAncestors(category);
    }

    private void addSubNodeCategory(Category category, Category parent) {
        // 查询兄弟节点数量 用于生成Code
        int count = categoryRepo.count(CategoryQueryCommand.builder()
                .level(parent.getLevel() + 1)
                .parentId(parent.getId())
                .build());
        category.linkParent(parent, count);
        categoryRepo.addCategory(category);
        categoryRepo.addCategory(parent);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return categoryRepo.selectCategory(id_);
                });
    }

    @Override
    public Optional<Category> getCategoryByCode(Long code) {
        return Optional.of(code)
                .map(id_ -> {
                     Category category =  categoryRepo.selectCategoryByCode(code);

                    if (ObjectUtils.isEmpty(category)) {
                        return null;
                    }

                    List<Attribute> attributes =  attributeRepo.select(AttributeQueryCommand.builder()
                                            .categoryCode(code)
                                            .build()).getRows();

                    category.setAttributes(attributes);

                    return category;
                });
    }

    @Override
    public boolean updateCategory(CategoryCommand command) {
        Category category = new Category();

        category.setId(command.getId());
        category.setParentId(command.getParentId());
        category.setName(command.getName());
        category.setCode(command.getCode());
        category.setLogo(command.getLogo());
        category.setStatus(command.getStatus());
        category.setOrderNum(command.getOrderNum());

        Category old = categoryRepo.updateCategory(category);
        if (ObjectUtils.isNotEmpty(old)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCategoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return false;
        }

        Category category = categoryRepo.selectCategory(id);

        if (!category.getLeaf()) {
            throw new MuyuanException(ResponseCode.FAIL.getCode(), "只能删除叶子节点");
        }

        List<Brand> brands = brandRepo.selectByCategoryCode(category.getCode());
        if (ObjectUtils.isNotEmpty(brands)) {
            throw new MuyuanException(ResponseCode.FAIL.getCode(), "有品牌关联当前分类");
        }

        List<Category> olds = categoryRepo.deleteBy(category.getId());
        List<Attribute> select = attributeRepo.select(AttributeQueryCommand
                .builder()
                .categoryCode(category.getCode())
                .build()).getRows();

        attributeRepo.deleteBy(select.stream().map(Attribute::getId).toArray(Long[]::new));

        return !olds.isEmpty();
    }

}
