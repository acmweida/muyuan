package com.muyuan.product.domains.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class Sku {

    private Long id;

    private Long goodsId;

    /**
     * 售价
     */
    private Double price;

    private String pic;

    /**
     * 锁定库存
     */
    private Integer stockLock;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 库存
     */
    private Integer stock;

    private String context;

    private Date createTime;

    private Date updateTime;

    public void initInstance() {
        createTime = DateTime.now().toDate();
    }

}
