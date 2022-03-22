package com.muyuan.system.interfaces.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysUserDTO implements Serializable {

    static final long serialVersionUID = 123456789L;

    private Long id;

    /**
     * 用户名 唯一用于登录
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

    /**
     * 角色
     */
    private List<String> roles;

    private List<String> permissions;
}
