package com.muyuan.product.domains.service;

import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductCategoryDomainService 接口
 * Description 产品分类域服务
 * @Author 2456910384
 * @Date 2022/6/9 15:41
 * @Version 1.0
 */
public interface GoodsCategoryDomainService {

    /**
     * 分类列表查询
     * @param goodsCategoryDTO
     * @return
     */
    List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO);

    /**
     * 新增分类
     * @param goodsCategoryDTO
     */
    void add(GoodsCategoryDTO goodsCategoryDTO);

    /**
     * 更新分类
     * @param goodsCategoryDTO
     */
    void update(GoodsCategoryDTO goodsCategoryDTO);

    /**
     * 唯一性查询
     * @param goodsCategory
     * @return
     */
    String checkUnique(GoodsCategory goodsCategory);

    /**
     * 获取详情
     * @param id
     * @return
     */
    Optional<GoodsCategory> get(GoodsCategory id);

    /**
     * 删除ID
     * @param ids
     */
    void delete(String[] ids);

}