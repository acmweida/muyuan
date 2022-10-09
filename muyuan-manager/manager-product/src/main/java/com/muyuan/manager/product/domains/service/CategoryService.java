package com.muyuan.manager.product.domains.service;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.manager.product.domains.dto.CategoryDTO;
import com.muyuan.manager.product.domains.model.Category;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductCategoryDomainService 接口
 * Description 产品分类域服务
 * @Author 2456910384
 * @Date 2022/6/9 15:41
 * @Version 1.0
 */
public interface CategoryService {

    /**
     * 分类列表查询
     *
     * @param categoryDTO
     * @return
     */
    List<Category> list(CategoryDTO categoryDTO);

    /**
     * 选择
     * @param
     * @return
     */
    List<SelectTree> treeSelect(Long parentId, Integer leaf);

    /**
     * 新增分类
     *
     * @param categoryDTO
     */
    void add(CategoryDTO categoryDTO);

    /**
     * 更新分类
     *
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 唯一性查询
     *
     * @param category
     * @return
     */
    String checkUnique(Category category);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    Optional<Category> get(Category id);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    Optional<Category> detail(Category id);

    /**
     * 删除ID
     *
     * @param ids
     */
    void delete(Long[] ids);

}