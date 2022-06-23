package com.muyuan.shop.domains.model;

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
     * 商店编码
     */
    private String shopNo;

    /**
     * 关联会员号
     */
    private String memberNo;

    /**
     * 关联会员ID
     */
    private long memberId;

    /**
     * 创建时间
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

    /**
     * 店铺地址
     */
    private long addressId;

    /**
     * 主营品牌
     */
    private String brands;

    /**
     * 支付账号信息
     */
    private long accountId;

    /**
     * 营业证照ID
     */
    private long certificateId;
}
