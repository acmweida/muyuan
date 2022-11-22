package com.muyuan.user.api.dto;

import com.muyuan.common.core.enums.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RolePermissionRequest
 * Description RolePermissionRequest
 * @Author 2456910384
 * @Date 2022/9/16 11:31
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest implements Serializable {

    private static final long serialVersionUID = 1357932148568l;

    private Long id;

    private PlatformType platformType;

    private String resource;

    private String type;

    private String business;

    private String module;

    private String status;

    private String perms;

    private Long resourceRef;
}
