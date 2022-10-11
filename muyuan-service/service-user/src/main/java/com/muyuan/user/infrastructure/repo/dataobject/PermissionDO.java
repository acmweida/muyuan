package com.muyuan.user.infrastructure.repo.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDO {

    private Long id;

    private String resource;

    private String action;

    /**
     * 平台类型
     */
    private Integer type;

    private String perms;

    private Integer status;
}
