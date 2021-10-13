package com.muyuan.product.domains.model;

import lombok.Data;

/**
 * 商品类型
 */
@Data
public class ProductCategory {

    private  long id;

    /**
     * 分类编码
     */
    private long code;

    /**
     * 父分类ID
     */
    private long parentId;

    private String name;

    private String logo;

    private short level;

    private int ProductCount;

    private boolean publish;

    private int sort;

    private String keywords;

}
