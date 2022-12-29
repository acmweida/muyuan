package com.muyuan.goods.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Feature;
import com.muyuan.goods.face.dto.FeatureQueryCommand;

import java.util.List;

/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 */

public interface FeatureRepo {

    Page<Feature> select(FeatureQueryCommand command);

    Feature selectFeature(Long id);

    Feature selectFeature(Feature.Identify identify);

    boolean addFeature(Feature feature);

    /**
     * 更新信息
     * @param feature
     * @return old value
     */
    Feature updateFeature(Feature feature);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<Feature> deleteBy(Long... ids);

}
