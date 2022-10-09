package com.muyuan.manager.system.domains.dto;

import com.muyuan.common.bean.BaseDTO;
import com.muyuan.manager.system.domains.model.SysRole;
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

    private int pageNum = 1;

    private int pageSize = 10;

    private boolean enablePage = true;

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
