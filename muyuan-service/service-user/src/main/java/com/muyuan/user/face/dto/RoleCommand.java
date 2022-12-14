package com.muyuan.user.face.dto;

import com.muyuan.common.bean.OptCommand;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.valueobject.RoleID;
import lombok.Data;

/**
 * @ClassName RoleDO
 * Description TODO
 * @Author 2456910384
 * @Date 2022/9/15 14:16
 * @Version 1.0
 */
@Data
public class RoleCommand extends OptCommand {

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

    private Long[] permissionIds;

}
