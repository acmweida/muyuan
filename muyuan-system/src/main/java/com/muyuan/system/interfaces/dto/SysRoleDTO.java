package com.muyuan.system.interfaces.dto;

import lombok.Data;

/**
 * @ClassName SysRoleDTO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@Data
public class SysRoleDTO {

    private int pageNum = 1;

    private int pageSize = 10;

    private String name;

    private String status;
}
