package com.muyuan.shop.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class Shop {

    private long id;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 关联会员号
     */
    private long memberNo;

    /**
     * 创建事件
     */
    private Date createTime;

    /**
     * 店铺标签
     */
    private String tag;

    /**
     * 类型 1-普通店铺 2-官方店铺 3-自营店铺
     */
    private short type;
}
