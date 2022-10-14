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
public class MenuQueryParams {

    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    @NotNull(message = "菜单排序不能为空")
    private Integer orderNum;

    /**
     * 组件路径
     */
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    private String component;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @NotBlank(message = "菜单类型不能为空")
    private String type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    private Integer platformType;

}
