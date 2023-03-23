package com.muyuan.goods.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.face.dto.CategoryCommand;
import com.muyuan.goods.face.dto.CategoryQueryCommand;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName CategoryDomainService 接口
 * Description 商品分类接口
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 * @Version 1.0
 */
public interface CategoryService {

    /**
     * 商品分类分页查询
     * @param command
     * @return
     */
    Page<Category> list(CategoryQueryCommand command);

    List<Category> listByBrandId(Long... brandIds);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    String checkUnique(Category.Identify identify);

    /**
     * 新增商品分类信息
     * @param command
     * @return
     */
    boolean addCategory(CategoryCommand command);

    /**
     * 查询商品分类信息
     * @param id
     * @return
     */
    Optional<Category> getCategory(Long id);

    /**
     * 查询商品分类信息
     * @param code
     * @return
     */
    Optional<Category> getCategoryByCode(Long code);

    /**
     * 更新商品分类信息
     * @param command
     * @return
     */
    boolean updateCategory(CategoryCommand command);

    /**
     * 删除商品分类信息
     * @param id
     * @return
     */
    boolean deleteCategoryById(Long id);
}
