package com.muyuan.user.domain.model.entity.user;

import com.muyuan.common.core.enums.UserType;
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

    private String resource;

    private String action;

    private UserType type;

    private String perms;

    private Integer status;
}
