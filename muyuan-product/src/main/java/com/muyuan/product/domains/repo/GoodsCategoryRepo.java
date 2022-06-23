package com.muyuan.product.domains.repo;

import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;

import java.util.List;

/**
 * @ClassName ProductCategoryRepo 接口
 * Description 商品分类 Repository
 * @Author 2456910384
 * @Date 2022/6/10 11:37
 * @Version 1.0
 */
public interface GoodsCategoryRepo {

    List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO);

    GoodsCategory selectOne(GoodsCategory goodsCategory);

    void insert(GoodsCategory goodsCategory);

    void update(GoodsCategory goodsCategory);

    int count(GoodsCategoryDTO goodsCategoryDTO);

    void delete(String[] ids);

}
