package com.muyuan.system.domain.model;

import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @ClassName User
 * Description 系统用户 t_sys_user
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
public class SysUser {

    private Long id;

    /**
     * 用户名 唯一 用于登录
     */
    private String username;

    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像图片
     */
    private String avatar;

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

    private Long createBy;

    public SysUser() {
    }

    public SysUser(String username, String password) {
        Assert.isTrue(!ObjectUtils.isEmpty(username),"username is null");
        Assert.isTrue(!ObjectUtils.isEmpty(password),"password is null");
        this.username = username;
        this.password = password;
    }
}
