package com.muyuan.manager.system.dto.vo;

import lombok.Data;

/**
 * @ClassName PermissionDTO
 * Description 权限信息实体
 * @Author 2456910384
 * @Date 2022/11/16 11:17
 * @Version 1.0
 */
@Data
public class PermissionVO {

    private Long id;

    private String business;

    private String module;

    private String platformType;

    private String resource;

    private String type;

    private String perms;

    private String status;
}
