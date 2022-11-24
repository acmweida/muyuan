package com.muyuan.user.api.dto;

import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RoleDO
 * Description TODO
 * @Author 2456910384
 * @Date 2022/9/15 14:16
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class RoleRequest implements Serializable {

    private static final long serialVersionUID = 12457932148502l;

    private Long id;

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

    private Long[] permissionIds;

}
