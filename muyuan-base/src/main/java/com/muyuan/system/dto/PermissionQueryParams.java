package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.*;

/**
 * @ClassName RolePermissionRequest
 * Description RolePermissionRequest
 * @Author 2456910384
 * @Date 2022/9/16 11:31
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionQueryParams extends PageDTO {

    private Integer platformType;

    private String resource;

    private String type;

    private String business;

    private String module;

    private String status;

    private String[] types;
}
