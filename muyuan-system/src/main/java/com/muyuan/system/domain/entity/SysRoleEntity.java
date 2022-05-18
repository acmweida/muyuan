package com.muyuan.system.domain.entity;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.model.SysRole;
import lombok.Data;

import java.util.Date;
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

    public static boolean isAdmin(String roleCode) {
        return (SecurityConst.ADMIN_ROOT_ROLE_CODE).equals(roleCode);
    }

    public static boolean isAdmin(List<String> roleCodes) {
        for (String roleCode : roleCodes) {
            if (isAdmin(roleCode)) {
                return true;
            }
        }
        return false;
    }

    public SysRoleEntity update() {
        this.setUpdateTime(new Date());
        this.setUpdateBy(SecurityUtils.getUserId());
        return this;
    }
}
