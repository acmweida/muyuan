package com.muyuan.goods.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.goods.api.dto.FeatureDTO;
import com.muyuan.goods.api.dto.FeatureQueryRequest;
import com.muyuan.goods.api.dto.FeatureRequest;

/**
 * 通用特征量Service接口
 *
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 */
public interface FeatureInterface {

    /**
      * 查询通用特征量列表
      * @param request
      * @return
      */
    Result<Page<FeatureDTO>> list(FeatureQueryRequest request);

    /**
     * 添加通用特征量
     * @param request
     * @return
     */
    Result add(FeatureRequest request);

    /**
     * 查询通用特征量
     * @param id
     * @return
     */
    Result<FeatureDTO> getFeature(Long id);

    /**
     * 更新通用特征量
     * @param request
     * @return
     */
    Result updateFeature(FeatureRequest request);

    /**
     *  删除通用特征量
     * @param ids
     * @return
     */
    Result deleteFeature(Long... ids);

}
