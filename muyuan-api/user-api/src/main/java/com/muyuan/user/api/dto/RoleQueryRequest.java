package com.muyuan.user.api.dto;

import com.muyuan.common.bean.PageDTO;
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
public class RoleQueryRequest extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1457332148568l;

    private PlatformType platformType;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;


    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;


}
