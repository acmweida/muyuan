package com.muyuan.product.domains.model;

import lombok.Data;

/**
 * 商品基本信息
 */
@Data
public class Product {

    private long id;

    /**
     * todo:物流模板
     */
    private long wuliouModel;

    /**
     * 商品标题
     */
    private String title;

    private boolean delete;

    /**
     * 品牌ID
     */
    private long brandId;

    /**
     * 分类id
     */
    private long categoryId;

    /**
     * 店铺ID
     */
    private long shopId;

    /**
     * 是否上架
     */
    private boolean publish;

    /***
     * 详情页面
     */
    private String detailUrl;

    /**
     * 主图
     */
    private String mainPictureUrl;

    /**
     * 商品标签
     */
    private String tags;

}
