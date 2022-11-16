package com.muyuan.user.domain.model.entity;

import com.muyuan.common.core.enums.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    private Long id;

    private String business;

    private String module;

    private PlatformType platformType;

    private String resource;

    private String type;

    private String perms;

    private Integer status;
}
