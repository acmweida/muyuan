package com.muyuan.goods.domains.model;

/**
 * 商品属性
 */
public class GoodsAttribute {

    private long id;

    private long goodsAttributeCategoryId;

    /**
     * 属性值选择类型
     */
    private int selectType;

    /**
     * 检索类型 0->不需要进行检索；1->关键字检索；2->范围检索
     */
    private short searchType;
}
