package com.muyuan.product.domains.model;

import lombok.Data;

@Data
public class Sku {

    private long id;

    private long ProductId;

    /**
     * 售价
     */
    private double price;

    private String pic;

    /**
     * 锁定库存
     */
    private double lockStock;

    /**
     * 销量
     */
    private double sale;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 库存
     */
    private double stock;

    private String skuData;

}
