package com.muyuan.manager.product.infrastructure.persistence;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.product.domains.dto.FeatureDTO;
import com.muyuan.manager.product.domains.model.Brand;
import com.muyuan.manager.product.domains.model.Feature;
import com.muyuan.manager.product.domains.repo.FeatureRepo;
import com.muyuan.manager.product.infrastructure.persistence.mapper.BrandCategoryMapper;
import com.muyuan.manager.product.infrastructure.persistence.mapper.FeatureMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Component
@AllArgsConstructor
public class FeatureRepoImpl implements FeatureRepo {

    private FeatureMapper featureMapper;

    private BrandCategoryMapper brandCategoryMapper;

    @Override
    public List<Feature> select(FeatureDTO featureDTO) {
        return select(featureDTO, null);
    }

    @Override
    public List<Feature> select(FeatureDTO featureDTO, Page page) {
        return featureMapper.selectList(new SqlBuilder(Brand.class)
                .like(NAME, featureDTO.getName())
                .eq(STATUS, featureDTO.getStatus())
                .page(page)
                .build());
    }

    @Override
    public Feature selectOne(FeatureDTO featureDTO) {
        return featureMapper.selectOne(new SqlBuilder(FeatureDTO.class)
                .eq(ID, featureDTO.getId())
                .eq(NAME, featureDTO.getName())
                .build());
    }

    @Override
    public void insert(Feature feature) {
        featureMapper.insert(feature);
    }

    @Override
    public void update(Feature feature) {
        featureMapper.updateBy(feature, ID);
    }

    @Override
    public void delete(Long... ids) {
        featureMapper.deleteBy(new SqlBuilder(Feature.class)
                .in(ID,ids)
                .build());
    }


}
