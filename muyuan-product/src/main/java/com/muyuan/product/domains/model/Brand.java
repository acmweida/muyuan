package com.muyuan.product.domains.model;

public class Brand {

    private long id;

    /**
     * 品牌名称
     */
    private String name;

    private String englishName;

    /**
     * 图标
     */
    private String logo;

    private boolean publish;

    /**
     * 分类id
     */
    private long categoryId;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 商品数量
     */
    private int ProductCount;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 描述
     */
    private String desc;
}
