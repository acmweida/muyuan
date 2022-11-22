package com.muyuan.user.face.dto;

import com.muyuan.common.bean.PageDTO;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.valueobject.UserID;
import lombok.Data;

/**
 * @ClassName PermissionQueryCommand
 * Description PermissionQueryCommand
 * @Author 2456910384
 * @Date 2022/9/16 11:37
 * @Version 1.0
 */
@Data
public class PermissionQueryCommand extends PageDTO {

    private UserID userId;

    private PlatformType platformType;

    private String resource;

    private String type;

    private String business;

    private String module;

    private String status;

    private Long resourceRef;
}
