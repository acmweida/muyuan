package com.muyuan.member.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Menu
 * Description 菜单权限表
 * @Author 2456910384
 * @Date 2022/1/27 16:38
 * @Version 1.0
 */
@Data
public class Menu {

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
    private byte frame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private char type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private char visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private char status;

    /**
     * 权限标识
     */
    private String perms;

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

    /**
     * 修改时间
     */
    private Date updateTime;

    private String createBy;

    private String updateBy;

}
