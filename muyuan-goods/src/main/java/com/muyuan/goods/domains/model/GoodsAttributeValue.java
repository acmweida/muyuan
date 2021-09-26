package com.muyuan.goods.domains.model;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsAttributeValue {

    private long id;

    private long goodsId;

    private long goodsAttributeId;

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
