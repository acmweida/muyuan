package com.muyuan.user.face.dto;

import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PermissionQueryCommand
 * Description PermissionQueryCommand
 * @Author 2456910384
 * @Date 2022/9/16 11:37
 * @Version 1.0
 */
@Data
public class PermissionQueryCommand {

    private List<String> roleCodes;

    private PlatformType platformType;
}
