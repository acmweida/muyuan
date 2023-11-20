package com.muyuan.store.system.domains.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @ClassName SysRoleDTO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@Data
public class RoleDTO {

    private Long id;

    private int pageNum = 1;

    private int pageSize = 10;

    @NotNull(message = "角色名称不能为空")
    private String name;

    private String status;

    @NotNull(message = "角色编码不能为空")
    private String code;

    private String[] menuIds;

    private String orderNum;

}
