package com.muyuan.manager.goods.domains.model;

import lombok.Data;

/**
 * @ClassName FeatureValue
 * Description 特征值
 * @Author 2456910384
 * @Date 2022/8/10 11:20
 * @Version 1.0
 */
@Data
public class FeatureValue {

    /**  */
    private Long id;

    /** 属性ID */
    private Long featureId;

    /** 属性值 */
    private String value;

    private Integer status;
}
