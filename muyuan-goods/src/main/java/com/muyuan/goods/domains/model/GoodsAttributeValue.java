package com.muyuan.goods.domains.model;

import lombok.Data;

@Data
public class GoodsAttributeValue {

    private long id;

    private long goodsId;

    private long goodsAttributeId;

    private String value;
}
