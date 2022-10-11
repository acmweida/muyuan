package com.muyuan.user.face.dto;

import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;

/**
 * @ClassName PermissionQueryCommand
 * Description PermissionQueryCommand
 * @Author 2456910384
 * @Date 2022/9/16 11:37
 * @Version 1.0
 */
@Data
public class PermissionQueryCommand {

    private Long userId;

    private PlatformType platformType;
}
