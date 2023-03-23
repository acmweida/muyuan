package com.muyuan.goods.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.face.dto.FeatureCommand;
import com.muyuan.goods.face.dto.FeatureQueryCommand;

import java.util.Optional;

/**
 * @ClassName FeatureDomainService 接口
 * Description 通用特征量接口
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
public interface FeatureService {

    /**
     * 通用特征量分页查询
     * @param command
     * @return
     */
    Page<Feature> list(FeatureQueryCommand command);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    String checkUnique(Feature.Identify identify);

    /**
     * 新增通用特征量信息
     * @param command
     * @return
     */
    boolean addFeature(FeatureCommand command);

    /**
     * 查询通用特征量信息
     * @param id
     * @return
     */
    Optional<Feature> getFeature(Long id);

    /**
     * 更新通用特征量信息
     * @param command
     * @return
     */
    boolean updateFeature(FeatureCommand command);

    /**
     * 删除通用特征量信息
     * @param ids
     * @return
     */
    boolean deleteFeatureById(Long... ids);
}
