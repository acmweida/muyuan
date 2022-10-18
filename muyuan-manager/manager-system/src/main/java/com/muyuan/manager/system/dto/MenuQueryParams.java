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
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;


    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    private Integer platformType;

}
