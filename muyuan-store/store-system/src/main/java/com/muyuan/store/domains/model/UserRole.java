package com.muyuan.store.domains.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserRole
 * Description 用户角色 t_user_role
 * @Author 2456910384
 * @Date 2021/12/24 10:38
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    private Long userId;

    private Long roleId;

}
