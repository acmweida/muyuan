package com.muyuan.manager.product.domains.service;


import com.muyuan.common.core.bean.SelectTree;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.product.domains.dto.FeatureDTO;
import com.muyuan.manager.product.domains.model.Feature;

import java.util.List;

/**
 * 通用特征量Service接口
 * 
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
public interface FeatureService
{
    String FEATURE_KEY_PREFIX = "FEATURE:";

    /**
     * 查询通用特征量
     *
     * @param id 通用特征量主键
     * @return 通用特征量
     */
    Feature get(Long id);

    /**
     * 查询通用特征量列表
     *
     * @param featureDTO 通用特征量
     * @return 通用特征量集合
     */
    List<Feature> list(FeatureDTO featureDTO);

    /**
     * 查询通用特征量列表
     *
     * @param featureDTO 通用特征量
     * @return 通用特征量集合
     */
    Page<Feature> page(FeatureDTO featureDTO);

    /**
     * 新增通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    void add(Feature feature);

    /**
     * 修改通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    void update(Feature feature);

    /**
     * 批量删除通用特征量
     * 
     * @param ids 需要删除的通用特征量主键集合
     * @return 结果
     */
    void delete(Long[] ids);

    List<SelectTree> options(FeatureDTO featureDTO);
}
