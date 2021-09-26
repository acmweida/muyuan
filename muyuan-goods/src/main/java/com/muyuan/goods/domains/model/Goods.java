package com.muyuan.goods.domains.model;

import lombok.Data;

/**
 * 商品基本信息
 */
@Data
public class Goods {

    private long id;

    /**
     * todo:物流模板
     */
    private long wuliouModel;

    /**
     * 商品标题
     */
    private String title;

    private boolean isDelete;

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
    private boolean isPublish;

    /***
     * 详情页面
     */
    private String detailUrl;

    /**
     * 主图
     */
    private String mainPicture;

}
