package com.muyuan.system.domain.entity;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.system.domain.model.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleEntity
 * Description RoleEntity
 * @Author 2456910384
 * @Date 2022/2/9 16:41
 * @Version 1.0
 */
@Data
public class SysRoleEntity extends SysRole {

    public boolean isShopKeeper() {
        return isAdmin(this.getCode());
    }

    public static boolean isAdmin(String roleName) {
        return (SecurityConst.ADMIN_ROOT_ROLE_CODE).equals(roleName);
    }

    public static boolean isAdmin(List<String> roleNames) {
        for (String roleName : roleNames) {
            if (isAdmin(roleName)) {
                return true;
            }
        }
        return false;
    }
}
