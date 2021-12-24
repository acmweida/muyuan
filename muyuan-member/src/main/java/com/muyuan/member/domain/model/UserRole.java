package com.muyuan.member.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserRole
 * Description 用户角色 t_user_role
 * @Author 2456910384
 * @Date 2021/12/24 10:38
 * @Version 1.0
 */
@Data
public class UserRole {

    private Long id;

    private Long userId;

    private Long roleId;

    /**
     * 状态 0-正常 1-删除  2-禁用
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

    private Long updateUserId;

    private Long createUserId;
}
