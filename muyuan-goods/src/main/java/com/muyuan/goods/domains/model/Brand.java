package com.muyuan.goods.domains.model;

public class Brand {

    private long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 图标
     */
    private String logo;

    private boolean  isPublish;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 商品数量
     */
    private int goodsCount;

    /**
     * 排序
     */
    private int sort;
}
