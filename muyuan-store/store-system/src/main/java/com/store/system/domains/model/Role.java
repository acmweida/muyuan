package com.store.system.domains.model;

import com.muyuan.common.core.constant.SecurityConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Role
 * Description 角色 t_role
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

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

    public Role(String code) {
        this.code = code;
    }

    public Role(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public static boolean isAdmin(String roleCode) {
        return (SecurityConst.SHOP_KEEPER_ROLE_CODE).equals(roleCode);
    }
}
