package com.muyuan.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName SysRoleDTO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@Schema(name = "角色")
@Data
public class RoleParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    public interface SelectUser {

    }

    public interface SelectUserOne {

    }


    @NotNull(groups = {Update.class,SelectUser.class,SelectUserOne.class})
    @Schema(name = "角色ID")
    private Long id;

    @NotBlank(message = "角色名称不能为空",groups = {Add.class,Update.class})
    @Schema(name = "角色名称")
    private String name;

    @Schema(name = "状态 默认 0-启用")
    private Integer status = 0;

    @NotNull(message = "角色编码不能为空",groups = {Add.class,Update.class})
    @Schema(name = "角色编码")
    private String code;

    @NotNull(message = "平台类型不能为空",groups = {Add.class,Update.class})
    @Schema(name = "平台类型")
    private Integer platformType;

    @Schema(name = "权限ID数组")
    private Long[] permissionIds;

    @Schema(name = "菜单ID数组")
    private Long[] menuIds;

    @Schema(name = "排序 默认 -0")
    private Integer orderNum = 0;

    @Size(min = 1,groups = SelectUser.class,message = "用户Id不能为空")
    @Schema(name = "用户ID")
    private Long[] userIds;

    @NotNull(groups = SelectUserOne.class,message = "用户Id不能为空")
    @Schema(name = "用户ID")
    private Long userId;

}
