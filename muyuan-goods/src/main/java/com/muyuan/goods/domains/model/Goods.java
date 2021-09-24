package com.muyuan.goods.domains.model;

import lombok.Data;

/**
 * 商品基本信息
 */
@Data
public class Goods {

    private long id;

    /**
     * 品牌ID
     */
    private long brandId;

    /**
     * 分类id
     */
    private long categoryId;

    /**
     * 商品属性分类id
     */
    private long goodsAttributeCategoryId;

    /**
     * 店铺ID
     */
    private long shopId;

    /**
     * 是否上架
     */
    private boolean isPublish;

}
