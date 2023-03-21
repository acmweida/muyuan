package com.muyuan.user.infrastructure.repo.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName UserRole
 * Description 用户角色 t_role_user
 * @Author 2456910384
 * @Date 2021/12/24 10:38
 * @Version 1.0
 */
@Data
@Builder
@TableName("t_user_role")
public class UserRoleDO {

    private Long userId;

    private Long roleId;

}
