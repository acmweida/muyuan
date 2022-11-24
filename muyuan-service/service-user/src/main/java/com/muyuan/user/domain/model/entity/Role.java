package com.muyuan.user.domain.model.entity;

import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.valueobject.RoleID;
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

    @Data
    static public class Identify {

        private RoleID id;

        private PlatformType platformType;

        private String code;

        public Identify(RoleID id, PlatformType platformType, String code) {
            this.id = id;
            this.platformType = platformType;
            this.code = code;
        }

        public Identify(PlatformType platformType, String code) {
            this.platformType = platformType;
            this.code = code;
        }
    }

    private RoleID id;

    private PlatformType platformType;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private Integer orderNum;

    /**
     * 状态 0-正常 1-停用
     */
    private Integer status;

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

    public Role(RoleID id, String code) {
        this.id = id;
        this.code = code;
    }

    public static boolean isAdmin(String roleCode) {
        return (SecurityConst.SHOP_KEEPER_ROLE_CODE).equals(roleCode);
    }
}
