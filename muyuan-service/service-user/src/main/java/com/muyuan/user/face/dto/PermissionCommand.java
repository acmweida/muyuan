package com.muyuan.user.face.dto;

import com.muyuan.common.bean.OptCommand;
import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;

/**
 * @ClassName RolePermissionRequest
 * Description RolePermissionRequest
 * @Author 2456910384
 * @Date 2022/9/16 11:31
 * @Version 1.0
 */
@Data
public class PermissionCommand extends OptCommand {

    private Long id;

    private PlatformType platformType;

    private String resource;

    private String type;

    private String business;

    private String module;

    private Integer status = 0;

    private String perms;

    private Long resourceRef;
}
