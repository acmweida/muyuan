package com.muyuan.goods.domains.model;

/**
 * 商品属性
 */
public class GoodsAttribute {

    private long id;

    /**
     * 属性分组
     */
    private String group;

    private long goodsCategoryId;

    /**
     * 属性值选择类型 1-单选 2-多选 3-自定义
     */
    private short selectType;

    /**
     * 属性类型 1-关键属性 2-销售属性 3-非关键属性 4-商品属性（新旧..）
     */
    private short attributeType;

    /**
     * 可选值
     */
    private String valueList;

    /**
     * 是否必填
     */
    private boolean isRequired;

}
