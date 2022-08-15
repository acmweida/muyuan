package com.muyuan.manager.product.domains.model;

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

    private Long id;

    private Long attributeId;

    private String value;

    private Integer status;
}
