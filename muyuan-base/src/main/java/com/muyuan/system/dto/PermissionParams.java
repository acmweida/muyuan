package com.muyuan.system.dto;

import com.muyuan.common.core.validator.annotions.NotNUllOnOther;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName PermissionDTO
 * Description 权限信息实体
 * @Author 2456910384
 * @Date 2022/11/16 11:17
 * @Version 1.0
 */
@Schema(name = "权限")
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

    @Schema(name = "ID")
    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(message = "分组不能为空", groups = {Add.class, Update.class})
    @Schema(name = "分组")
    private String business;

    @NotBlank(message = "模块不能为空", groups = {Add.class, Update.class})
    @Schema(name = "模块")
    private String module;

    @NotBlank(message = "平台类型不能为空", groups = {Add.class, Update.class})
    @Schema(name = "平台类型")
    private String platformType;

    @NotBlank(message = "权限名称不能为空", groups = {Add.class, Update.class})
    @Schema(name = "权限名称")
    private String resource;

    @NotBlank(message = "权限类型不能为空", groups = {Add.class, Update.class})
    @Schema(name = "权限类型")
    private String type;

    @NotBlank(message = "权限表达式不能为空", groups = {Add.class, Update.class})
    @Schema(name = "权限表达式")
    private String perms;

    @Schema(name = "状态 默认 0-启用")
    private Integer status = 0;

    @Schema(name = "关联资源ID")
    private Long resourceRef;
}
