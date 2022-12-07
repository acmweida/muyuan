package com.muyuan.user.face.dto;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.model.valueobject.UserID;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName MenuQueryCommand
 * Description MenuQueryCommand
 * @Author 2456910384
 * @Date 2022/9/16 11:37
 * @Version 1.0
 */
@Data
@Builder
public class MenuQueryCommand {

    private RoleCode[] roleCodes;

    private PlatformType platformType;

    private String name;

    private String status;

    private Long parentId;

    private Long[] parentIds;

    private String[] permissions;

    private UserID userID;

}
