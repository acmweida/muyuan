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
        return isShopKeeper(this.getName());
    }

    public static boolean isShopKeeper(String roleName) {
        return SecurityConst.SHOP_KEEPER_ROLE_CODE.equals(roleName);
    }

    public static boolean isShopKeeper(List<String> roleNames) {
        for (String roleName : roleNames) {
            if (isShopKeeper(roleName)) {
                return true;
            }
        }
        return false;
    }
}
