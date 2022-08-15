package com.muyuan.manager.product.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.manager.product.domains.model.BrandCategory;
import com.muyuan.manager.product.domains.model.Category;
import com.muyuan.manager.product.domains.dto.CategoryDTO;

import java.util.List;

/**
 * @ClassName ProductCategoryRepo 接口
 * Description 商品分类 Repository
 * @Author 2456910384
 * @Date 2022/6/10 11:37
 * @Version 1.0
 */
public interface CategoryRepo extends BaseRepo {

    String LEVEL = "level";

    String NAME = "name";

    String CODE = "code";

    String LEAF = "leaf";

    String STATUS_DELETE = "2";

    String ANCESTORS = "ancestors";

    Object[] STATUS_OK = new String[]{"0","1"};

    List<Category> list(CategoryDTO categoryDTO);

    List<Category> list(CategoryDTO categoryDTO, String... column);

    Category selectOne(Category category);

    void insert(Category category);

    void update(Category category);

    void update(Category category, String... column);

    /**
     * Count 兄弟节点
     *
     * @param categoryDTO
     * @return
     */
    int count(CategoryDTO categoryDTO);

    /**
     * Count 兄弟节点 包括被删除节点
     *
     * @param categoryDTO
     * @return
     */
    int countAll(CategoryDTO categoryDTO);

    void delete(Long[] ids);

    List<BrandCategory> selectBrand(Long... categoryCode);

}
