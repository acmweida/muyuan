package com.muyuan.manager.product.domains.repo;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.product.domains.dto.FeatureDTO;
import com.muyuan.manager.product.domains.model.Feature;

import java.util.List;

/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */

public interface FeatureRepo {

    List<Feature> select(FeatureDTO featureDTO);

    List<Feature> select(FeatureDTO featureDTO, Page page);

    Feature selectOne(Feature featureDTO);

    void insert(Feature feature);

    void update(Feature feature);

    void delete(String... ids);

}
