package com.muyuan.member.interfaces.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private long id;

    /**
     * 用户名 唯一用于登录
     */
    private String username;

    /**
     * 用户编号
     */
    private long userNo;

    /**
     * 用户类型 0-个人 1-企业
     */
    private short type;

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
     * 账号状态 0-正常 1-删除 2-锁定
     */
    private short status;

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
