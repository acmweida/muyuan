package com.muyuan.manager.system.dto;

import com.muyuan.common.core.validator.annotions.NotNUllOnOther;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @ClassName PermissionDTO
 * Description 权限信息实体
 * @Author 2456910384
 * @Date 2022/11/16 11:17
 * @Version 1.0
 */
@ApiModel("权限")
@Data
@NotNUllOnOther(value = "resourceRef",other = "type",otherValues = {"M","C"},message = "当类型为目录、菜单时，资源ID不能为空", groups = {PermissionParams.Add.class, PermissionParams.Update.class})
public class PermissionParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    @ApiModelProperty(value = "ID", required = true)
    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(message = "分组不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "分组", required = true)
    private String business;

    @NotBlank(message = "模块不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "模块", required = true)
    private String module;

    @NotBlank(message = "平台类型不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "平台类型", required = true)
    private String platformType;

    @NotBlank(message = "权限名称不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "权限名称", required = true)
    private String resource;

    @NotBlank(message = "权限类型不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "权限类型", required = true)
    private String type;

    @NotBlank(message = "权限表达式不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "权限表达式", required = true)
    private String perms;

    @ApiModelProperty(value = "状态 默认 0-启用")
    private Integer status = 0;

    @ApiModelProperty(value = "关联资源ID")
    private Long resourceRef;
}
