package com.muyuan.user.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.valueobject.Opt;
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
@NoArgsConstructor
public class PermissionRequest extends OptRequest implements Serializable {

    @Builder
    public PermissionRequest(Opt opt, Long id, PlatformType platformType, String resource, String type, String business, String module, String status, String perms, Long resourceRef) {
        super(opt);
        this.id = id;
        this.platformType = platformType;
        this.resource = resource;
        this.type = type;
        this.business = business;
        this.module = module;
        this.status = status;
        this.perms = perms;
        this.resourceRef = resourceRef;
    }

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
