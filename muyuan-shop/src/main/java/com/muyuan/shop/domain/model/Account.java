package com.muyuan.shop.domain.model;

public class Account {

    private long id;

    /**
     * 绑定账号类型 1-银行卡 2-支付宝 3-微信
     */
    private short type;

    /**
     * 账号号码 银行卡号/支付宝账号号/微信账号
     */
    private String code;

    private String createTime;
}
