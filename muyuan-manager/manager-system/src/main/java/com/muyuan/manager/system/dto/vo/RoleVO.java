package com.muyuan.manager.system.dto.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysRoleVO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/5/9 15:39
 * @Version 1.0
 */
@Data
public class RoleVO {

    private Long id;

    private Integer platformType;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private String orderNum;

    /**
     * 状态 0-正常 1-停用
     */
    private String status;

    private Long createBy;

    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
