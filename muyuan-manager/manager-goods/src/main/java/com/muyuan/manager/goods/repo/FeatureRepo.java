package com.muyuan.manager.goods.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.common.BaseRepo;
import com.muyuan.manager.goods.dto.FeatureDTO;
import com.muyuan.manager.goods.model.Feature;

import java.util.List;

/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */

public interface FeatureRepo extends BaseRepo {


    List<Feature> select(FeatureDTO featureDTO);

    List<Feature> select(FeatureDTO featureDTO, Page page);

    Feature selectOne(FeatureDTO featureDTO);

    void insert(Feature feature);

    void update(Feature feature);

    void delete(Long... ids);

}
