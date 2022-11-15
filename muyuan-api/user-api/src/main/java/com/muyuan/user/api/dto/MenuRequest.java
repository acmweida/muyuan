package com.muyuan.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RolePermissionRequest
 * Description RolePermissionRequest
 * @Author 2456910384
 * @Date 2022/9/16 11:31
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest implements Serializable {

    private static final long serialVersionUID = 1457932148568l;

    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    private String frame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;


    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;


    /** 是否缓存（0缓存 1不缓存） */
    private String cache;

    /**
     * 修改时间
     */
    private Date updateTime;

    private Long createBy;

    private Long updateBy;

    private String platformType;

}
