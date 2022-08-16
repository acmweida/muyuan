package com.muyuan.manager.product.domains.service.impl;

import com.muyuan.manager.product.domains.model.Feature;
import com.muyuan.manager.product.domains.service.FeatureService;
import com.muyuan.manager.product.infrastructure.persistence.mapper.FeatureMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用特征量Service业务层处理
 * 
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@Service
@AllArgsConstructor
@Slf4j
public class FeatureServiceImpl implements FeatureService
{
    private FeatureMapper featureMapper;

    /**
     * 查询通用特征量
     * 
     * @param id 通用特征量主键
     * @return 通用特征量
     */
    @Override
    public Feature selectFeatureById(Long id)
    {
        return featureMapper.selectFeatureById(id);
    }

    /**
     * 查询通用特征量列表
     * 
     * @param feature 通用特征量
     * @return 通用特征量
     */
    @Override
    public List<Feature> selectFeatureList(Feature feature)
    {
        return featureMapper.selectFeatureList(feature);
    }

    /**
     * 新增通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    @Override
    public int insertFeature(Feature feature)
    {
        feature.setCreateTime(DateTime.now().toDate());
        return featureMapper.insertFeature(feature);
    }

    /**
     * 修改通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    @Override
    public int updateFeature(Feature feature)
    {
        return featureMapper.updateFeature(feature);
    }

    /**
     * 批量删除通用特征量
     * 
     * @param ids 需要删除的通用特征量主键
     * @return 结果
     */
    @Override
    public int deleteFeatureByIds(Long[] ids)
    {
        return featureMapper.deleteFeatureByIds(ids);
    }

    /**
     * 删除通用特征量信息
     * 
     * @param id 通用特征量主键
     * @return 结果
     */
    @Override
    public int deleteFeatureById(Long id)
    {
        return featureMapper.deleteFeatureById(id);
    }
}
