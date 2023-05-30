package com.muyuan.goods.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.domains.repo.FeatureRepo;
import com.muyuan.goods.face.dto.FeatureQueryCommand;
import com.muyuan.goods.infrastructure.repo.converter.FeatureEntity2DOConverter;
import com.muyuan.goods.infrastructure.repo.dataobject.FeatureDO;
import com.muyuan.goods.infrastructure.repo.mapper.FeatureMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FeatureRepoImpl implements FeatureRepo {

    private FeatureMapper featureMapper;

    private FeatureEntity2DOConverter converter;

    @Override
    public Page<Feature> select(FeatureQueryCommand command) {
        LambdaQueryWrapper<FeatureDO> wrapper = new LambdaQueryWrapper<FeatureDO>()
                .eq(FeatureDO::getId,command.getId())
                .eq(FeatureDO::getName,command.getName())
                .eq(FeatureDO::getParentId,command.getParentId())
                .eq(FeatureDO::getLeaf,command.getLeaf())
                .eq(FeatureDO::getStatus,command.getStatus());

        Page<Feature> page = Page.<Feature>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<FeatureDO> list = command.enablePage() ?
                featureMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                featureMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Feature selectFeature(Long id) {
        FeatureDO featureDO = featureMapper.selectOne(new LambdaQueryWrapper<FeatureDO>()
                .eq(FeatureDO::getId, id));
        return converter.to(featureDO);
    }

    @Override
    public Feature selectFeature(Feature.Identify identify) {
        FeatureDO featureDO = featureMapper.selectOne(new LambdaQueryWrapper<FeatureDO>()
                .select(FeatureDO::getId)
                .eq(FeatureDO::getId, identify.getId()));

        return converter.to(featureDO);
    }

    @Override
    public boolean addFeature(Feature feature) {
        FeatureDO to = converter.to(feature);
        Integer count = featureMapper.insert(to);
        return count > 0;
    }

    @Override
    public Feature updateFeature(Feature feature) {
        LambdaQueryWrapper<FeatureDO> wrapper = new LambdaQueryWrapper<FeatureDO>()
                .eq(FeatureDO::getId, feature.getId());

        FeatureDO featureDO = featureMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(featureDO)) {
            featureMapper.updateById(converter.to(feature));
        }

        return converter.to(featureDO);
    }

    @Override
    public List<Feature> deleteBy(Long... ids) {
        LambdaQueryWrapper<FeatureDO> wrapper = new LambdaQueryWrapper<FeatureDO>()
                .in(FeatureDO::getId, ids);
        List<FeatureDO> features = featureMapper.selectList(wrapper);

        featureMapper.delete(wrapper);

        return converter.to(features);
    }

}
