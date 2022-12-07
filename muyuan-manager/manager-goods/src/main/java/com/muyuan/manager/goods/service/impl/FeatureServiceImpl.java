package com.muyuan.manager.goods.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Paging;
import com.muyuan.common.bean.SelectTree;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.manager.goods.dto.assembler.FeatureAssembler;
import com.muyuan.manager.goods.dto.FeatureDTO;
import com.muyuan.manager.goods.model.Feature;
import com.muyuan.manager.goods.repo.FeatureRepo;
import com.muyuan.manager.goods.service.FeatureService;
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
    private FeatureRepo repo;

    private RedisCacheService redisCacheService;


    /**
     * 查询通用特征量
     * 
     * @param id 通用特征量主键
     * @return 通用特征量
     */
    @Override
    public Feature get(Long id)
    {
        return repo.selectOne(FeatureDTO.builder()
                .id(id)
                .build());
    }

    /**
     * 查询通用特征量列表
     *
     * @param featureDTO 通用特征量
     * @return 通用特征量
     */
    @Override
    public Page<Feature> page(FeatureDTO featureDTO)
    {
        Page page = Page.newInstance((Paging) featureDTO);
        List<Feature>  features = repo.select(featureDTO,page);
        page.setRows(features);

        return page;
    }


    /**
     * 查询通用特征量列表
     * 
     * @param featureDTO 通用特征量
     * @return 通用特征量
     */
    @Override
    public List<Feature> list(FeatureDTO featureDTO)
    {
        return repo.select(featureDTO);
    }

    /**
     * 新增通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    @Override
    public void add(Feature feature)
    {
        feature.setCreateTime(DateTime.now().toDate());
        repo.insert(feature);
    }

    /**
     * 修改通用特征量
     * 
     * @param feature 通用特征量
     * @return 结果
     */
    @Override
    public void update(Feature feature)
    {
        repo.update(feature);
    }

    /**
     * 批量删除通用特征量
     * 
     * @param ids 需要删除的通用特征量主键
     * @return 结果
     */
    @Override
    public void delete(Long[] ids)
    {
        repo.delete(ids);
    }

    @Override
    public List<SelectTree> options(FeatureDTO featureDTO) {
        String key = FEATURE_KEY_PREFIX + featureDTO.getName();
        return CacheServiceUtil.getAndUpdateList(redisCacheService,key,
                () -> {
                    List<Feature> features = repo.select(featureDTO);
                    return FeatureAssembler.buildSelect(features);
                },
                SelectTree.class);
    }
}
