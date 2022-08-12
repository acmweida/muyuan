package com.muyuan.shop.domains.model;

import java.util.Date;

/**
 * 地址
 */
public class Address {

    private long id;

    /**
     * 三级地址ID
     */
    private long commonAddressId;

    /**
     * 详细地址
     */
    private String detail;

    private Date createTime;
}
