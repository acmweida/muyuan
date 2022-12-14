package com.muyuan.user.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RolePermissionRequest
 * Description RolePermissionRequest
 * @Author 2456910384
 * @Date 2022/9/16 11:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class MenuRequest extends OptRequest implements Serializable {

    @Builder
    public MenuRequest(Opt opt, Long id, String name, Long parentId, Integer orderNum, String path, String component, String query, String frame, String type, String visible, String status, String icon, String remark, String cache, String platformType) {
        super(opt);
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.path = path;
        this.component = component;
        this.query = query;
        this.frame = frame;
        this.type = type;
        this.visible = visible;
        this.status = status;
        this.icon = icon;
        this.remark = remark;
        this.cache = cache;
        this.platformType = platformType;
    }

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

    /** 是否缓存（0缓存 1不缓存） */
    private String cache;

    private String platformType;

}
