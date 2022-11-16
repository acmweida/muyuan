package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.BaseDTO;
import com.muyuan.manager.system.model.SysRole;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SysRoleDTO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@Data
public class SysRoleDTO extends BaseDTO<SysRoleDTO, SysRole> {

    private Long id;

    @NotNull(message = "角色名称不能为空")
    private String name;

    private String status;

    @NotNull(message = "角色编码不能为空")
    private String code;

    private Long[] menuIds;

    private String orderNum;

    private String username;

    private String phone;
}
