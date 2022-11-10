package com.muyuan.manager.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @ClassName SysMenuDTO
 * Description 菜单DTO
 * @Author 2456910384
 * @Date 2022/4/15 13:55
 * @Version 1.0
 */
@Data
public class MenuParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    @NotBlank(message = "ID不能为空",groups = Update.class)
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空",groups = {Update.class,Add.class})
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符",groups = {Update.class,Add.class})
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    @NotNull(message = "菜单排序不能为空")
    private Integer orderNum = 0;

    /**
     * 路由地址
     */
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符",groups = {Update.class,Add.class})
    private String path;

    /**
     * 组件路径
     */
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符",groups = {Update.class,Add.class})
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
    @NotBlank(message = "菜单类型不能为空",groups = {Update.class,Add.class})
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
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符",groups = {Update.class,Add.class})
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
    private String cache;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysMenuDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", path='").append(path).append('\'');
        sb.append(", component='").append(component).append('\'');
        sb.append(", query='").append(query).append('\'');
        sb.append(", frame='").append(frame).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", visible='").append(visible).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", perms='").append(perms).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", cache=").append(cache);
        sb.append('}');
        return sb.toString();
    }

}
