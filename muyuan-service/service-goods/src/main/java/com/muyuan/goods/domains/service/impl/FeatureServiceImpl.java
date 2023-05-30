package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.domains.repo.FeatureRepo;
import com.muyuan.goods.domains.service.FeatureService;
import com.muyuan.goods.face.dto.FeatureCommand;
import com.muyuan.goods.face.dto.FeatureQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName FeatureDomainServiceImpl
 * Description 权限
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private FeatureRepo featureRepo;

    @Override
    public Page<Feature> list(FeatureQueryCommand command) {
        return featureRepo.select(command);
    }

    @Override
    public boolean exists(Feature.Identify identify) {
        Long id = null == identify.getId() ? null : identify.getId();
        Feature feature = featureRepo.selectFeature(identify);
        if (null != feature && !id.equals(feature.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addFeature(FeatureCommand command) {
        Feature feature = new Feature();

            feature.setName(command.getName());
            feature.setParentId(command.getParentId());
            feature.setLeaf(command.getLeaf());
            feature.setStatus(command.getStatus());
            feature.setCreator(command.getCreator());
            feature.setCreateBy(command.getCreateBy());
            feature.setCreateTime(command.getCreateTime());

        return featureRepo.addFeature(feature);
    }

    @Override
    public Optional<Feature> getFeature(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return featureRepo.selectFeature(id_);
                });
    }

    @Override
    public boolean updateFeature(FeatureCommand command) {
        Feature feature = new Feature();

        feature.setId(command.getId());
        feature.setName(command.getName());
        feature.setParentId(command.getParentId());
        feature.setLeaf(command.getLeaf());
        feature.setStatus(command.getStatus());
        feature.setCreator(command.getCreator());
        feature.setCreateBy(command.getCreateBy());
        feature.setCreateTime(command.getCreateTime());

        Feature old = featureRepo.updateFeature(feature);
        if (ObjectUtils.isNotEmpty(old)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFeatureById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList(Arrays.asList(ids));

        List<Feature> olds = featureRepo.deleteBy(removeIds.toArray(new Long[0]));

        return !olds.isEmpty();
    }


}
