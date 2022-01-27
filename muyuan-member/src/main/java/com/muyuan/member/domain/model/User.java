package com.muyuan.member.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Role
 * Description 用户 t_user
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
public class User {

    private Long id;

    /**
     * 用户名 唯一 用于登录
     */
    private String username;

    private String nickName;

    /**
     * 店铺号
     */
    private Long shopNo;

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
    private char status;

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

    private Long updateUserId;

    private Long createUserId;
}
