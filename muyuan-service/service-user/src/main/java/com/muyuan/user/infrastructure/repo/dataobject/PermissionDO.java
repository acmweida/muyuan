package com.muyuan.user.infrastructure.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_permission")
public class PermissionDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String business;

    private String module;

    private String platformType;

    private String resource;

    private String type;

    private String perms;

    private Integer status;

    private Long resourceRef;
}
