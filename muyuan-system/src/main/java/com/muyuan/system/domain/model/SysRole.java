package com.muyuan.system.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Role
 * Description 角色 t_role
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
public class SysRole {

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 名称描述
     */
    private String nameDesc;

    /**
     * 状态 0-正常 1-删除
     */
    private short state;

    /**
     * 父角色ID
     */
    private Long parentId;

    private String createBy;

    private String updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
