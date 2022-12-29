package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.domains.repo.FeatureRepo;
import com.muyuan.goods.face.dto.FeatureQueryCommand;
import com.muyuan.goods.infrastructure.converter.FeatureConverter;
import com.muyuan.goods.infrastructure.dataobject.FeatureDO;
import com.muyuan.goods.infrastructure.mapper.FeatureMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;
import static com.muyuan.goods.infrastructure.mapper.FeatureMapper.LEAF;

@Component
@AllArgsConstructor
public class FeatureRepoImpl implements FeatureRepo {

    private FeatureMapper featureMapper;

    private FeatureConverter converter;

    @Override
    public Page<Feature> select(FeatureQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(FeatureDO.class)
                .eq(ID,command.getId())
                .eq(NAME,command.getName())
                .eq(PARENT_ID,command.getParentId())
                .eq(LEAF,command.getLeaf())
                .eq(STATUS,command.getStatus());

        Page<Feature> page = Page.<Feature>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<FeatureDO> list = featureMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Feature selectFeature(Long id) {
        FeatureDO featureDO = featureMapper.selectOne(new SqlBuilder(FeatureDO.class)
                .eq(ID, id)
                .build());
        return converter.to(featureDO);
    }

    @Override
    public Feature selectFeature(Feature.Identify identify) {
        FeatureDO featureDO = featureMapper.selectOne(new SqlBuilder(FeatureDO.class).select(ID)
                .eq(ID, identify.getId())
                .build());

        return converter.to(featureDO);
    }

    @Override
    public boolean addFeature(Feature feature) {
        FeatureDO to = converter.to(feature);
        Integer count = featureMapper.insertAuto(to);
        return count > 0;
    }

    @Override
    public Feature updateFeature(Feature feature) {
        SqlBuilder sqlBuilder = new SqlBuilder(FeatureDO.class)
                .eq(ID, feature.getId());

        FeatureDO featureDO = featureMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(featureDO)) {
            featureMapper.updateBy(converter.to(feature), ID);
        }

        return converter.to(featureDO);
    }

    @Override
    public List<Feature> deleteBy(Long... ids) {
        List<FeatureDO> features = featureMapper.selectList(new SqlBuilder(FeatureDO.class)
                .in(ID, ids)
                .build());

        featureMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(features);
    }

}
