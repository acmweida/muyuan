package com.muyuan.user.face.dto;

import com.muyuan.common.bean.PageDTO;
import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleQueryCommand extends PageDTO {

    private PlatformType platformType;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    private String status;
}
