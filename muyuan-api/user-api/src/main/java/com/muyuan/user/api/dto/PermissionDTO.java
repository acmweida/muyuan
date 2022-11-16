package com.muyuan.user.api.dto;

import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PermissionDTO
 * Description 权限信息实体
 * @Author 2456910384
 * @Date 2022/11/16 11:17
 * @Version 1.0
 */
@Data
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 1451932148501l;

    private Long id;

    private String business;

    private String module;

    private PlatformType platformType;

    private String resource;

    private String type;

    private String perms;

    private Integer status;
}
