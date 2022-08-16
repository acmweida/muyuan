package com.muyuan.manager.product.domains.service;


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
    /**
     * 查询通用特征量
     *
     * @param id 通用特征量主键
     * @return 通用特征量
     */
    Feature selectFeatureById(Long id);

    /**
     * 查询通用特征量列表
     *
     * @param feature 通用特征量
     * @return 通用特征量集合
     */
    List<Feature> selectFeatureList(Feature feature);

    /**
     * 新增通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    int insertFeature(Feature feature);

    /**
     * 修改通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    int updateFeature(Feature feature);

    /**
     * 批量删除通用特征量
     * 
     * @param ids 需要删除的通用特征量主键集合
     * @return 结果
     */
    int deleteFeatureByIds(Long[] ids);

    /**
     * 删除通用特征量信息
     *
     * @param id 通用特征量主键
     * @return 结果
     */
    int deleteFeatureById(Long id);
}
