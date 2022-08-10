package com.muyuan.product.domains.model;

import lombok.Data;

/**
 * @ClassName AttributeValue
 * Description 类目属性值
 * @Author 2456910384
 * @Date 2022/8/10 10:56
 * @Version 1.0
 */
@Data
public class AttributeValue {

    private Long id;

    private Long attributeId;

    private String value;
}
