package com.muyuan.member.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户编号
     */
    private long userNo;

    /**
     * 账号名唯一用于登录
     */
    private String account;

    /**
     * 用户类型 0-个人 1-企业
     */
    private short type;

    /**
     * 是否开店
     */
    private boolean isShop;

    /**
     * 店铺号
     */
    private long shopNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密salt
     */
    private String salt;

    /**
     * 加密key
     */
    private String encryptKey;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否删除（0-否 1-是）
     */
    private byte isDelete;

    /**
     * 账号状态 0-正常 1-锁定
     */
    private byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上次登录时间
     */
    private Date lastSignTime;
}
