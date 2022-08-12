package com.muyuan.store.domains.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuVO
 * Description MenuVO
 * @Author 2456910384
 * @Date 2022/2/11 15:56
 * @Version 1.0
 */
@Data
public class MenuVO {

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

    /** 是否缓存（0缓存 1不缓存） */
    private byte cache;


    private List<MenuVO> children = new ArrayList<>(0);
}
