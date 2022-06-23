package com.muyuan.system.domains.model;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName UserRole
 * Description 用户角色 t_user_role
 * @Author 2456910384
 * @Date 2021/12/24 10:38
 * @Version 1.0
 */
@Data
@Builder
public class SysUserRole {

    private Long userId;

    private Long roleId;

}
