package com.muyuan.common.member.domain.entity;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.member.domain.model.Role;
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
public class RoleEntity extends Role {

    public boolean isShopKeeper() {
        return isShopKeeper(this.getName());
    }

    public static boolean isShopKeeper(String roleName) {
        return (SecurityConst.AUTHORITY_PREFIX+SecurityConst.SHOP_KEEPER_ROLE_CODE).equals(roleName);
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
