package com.muyuan.manager.product.infrastructure.persistence.mapper;

import com.muyuan.manager.product.domains.model.FeatureValue;
import com.muyuan.common.mybatis.jdbc.ProductBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通用特征值Mapper接口
 *
 * @author ${author}
 * @date 2022-08-16T14:09:24.599+08:00
 */
@Mapper
public interface FeatureValueMapper extends ProductBaseMapper<FeatureValue> {

    String FEATURE_ID = "featureId";

    String VALUE = "value";

    /**
     * 查询通用特征值
     *
     * @param id 通用特征值主键
     * @return 通用特征值
     */
    FeatureValue selectFeatureValueById(Long id);

    /**
     * 查询通用特征值列表
     *
     * @param featureValue 通用特征值
     * @return 通用特征值集合
     */
    List<FeatureValue> selectFeatureValueList(FeatureValue featureValue);

    /**
     * 新增通用特征值
     *
     * @param featureValue 通用特征值
     * @return 结果
     */
    int insertFeatureValue(FeatureValue featureValue);

    /**
     * 修改通用特征值
     *
     * @param featureValue 通用特征值
     * @return 结果
     */
    int updateFeatureValue(FeatureValue featureValue);

    /**
     * 删除通用特征值
     *
     * @param id 通用特征值主键
     * @return 结果
     */
    int deleteFeatureValueById(Long id);

    /**
     * 批量删除通用特征值
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFeatureValueByIds(Long[] ids);
}
