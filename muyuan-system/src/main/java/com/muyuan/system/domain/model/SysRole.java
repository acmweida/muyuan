package com.muyuan.system.domain.model;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.web.util.SecurityUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Role
 * Description 角色 t_role
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
public class SysRole {

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private String orderNum;

    /**
     * 状态 0-正常 1-停用
     */
    private String status;

    private Long createBy;

    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public SysRole() {
    }

    public SysRole(String code) {
        this.code = code;
    }

    public SysRole(Long id, String code) {
        this.id = id;
        this.code = code;
    }

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

    public SysRole update() {
        this.setUpdateTime(new Date());
        this.setUpdateBy(SecurityUtils.getUserId());
        return this;
    }
}
