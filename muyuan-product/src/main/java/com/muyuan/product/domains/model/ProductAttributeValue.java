package com.muyuan.product.domains.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductAttributeValue {

    private long id;

    private long ProductId;

    private long ProductAttributeId;

    private String value;

    private Date createTime;

    private Date updateTime;

    private long createId;

    private long uodateId;

    /**
     * 是否历史版本
     */
    private boolean isHistory;
}
