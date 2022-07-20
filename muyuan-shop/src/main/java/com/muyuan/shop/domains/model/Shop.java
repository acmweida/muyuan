package com.muyuan.shop.domains.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class Shop {

    private static int num = 1;

    private Long id;

    /**
     * 店铺名称
     */
    private String name;

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
    private Integer type;

//    /**
//     * 店铺地址
//     */
//    private long addressId;

//    /**
//     * 主营品牌
//     */
//    private String brands;
//
//    /**
//     * 支付账号信息
//     */
//    private long accountId;
//
//    /**
//     * 营业证照ID
//     */
//    private long certificateId;

    public void buildId(Long userId) {
        DateTime now = DateTime.now();
        Long id = Long.valueOf(now.toString("yyMMddHHmmss"));
        id = (id * 10000 + userId % 10000 ) * 10000 + num ++;

    }

    public static void main(String[] args) {
        DateTime now = DateTime.now();
        Long id = Long.valueOf(now.toString("yyMMddHH"));
        id = (id * 10000 + (53289965500530688l % 10000) ) * 1000 + num ++;
        System.out.println(id);
    }
}
