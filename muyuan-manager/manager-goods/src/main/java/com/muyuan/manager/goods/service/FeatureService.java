package com.muyuan.manager.goods.service;


import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.goods.api.dto.FeatureDTO;
import com.muyuan.goods.api.dto.FeatureRequest;
import com.muyuan.manager.goods.dto.FeatureQueryParams;

import java.util.List;
import java.util.Optional;

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
     * 通用特征量查询
     * @param id
     * @return
     */
    Optional<FeatureDTO> get(Long id);

    /**
     * 查询通用特征量列表
     *
     * @param params 通用特征量
     * @return 通用特征量集合
     */
    Page<FeatureDTO> list(FeatureQueryParams params);


    /**
     * 通用特征量添加
     * @param request
     */
    Result add(FeatureRequest request);

    /**
     * 通用特征量变更
     * @param request
     * @return
     */
    Result update(FeatureRequest request);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);

    List<SelectTree> options(FeatureQueryParams params);
}
