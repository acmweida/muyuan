package com.muyuan.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@Data
@ApiModel("运营账户")
public class OperatorParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    public interface AuthRole {

    }

    @Pattern(regexp = "[a-zA-Z0-9_-]{4,16}$",message = "用户名只能由字母、数字、下划线组成，且长度是4-16位",groups = {Add.class,Update.class})
    @NotBlank(message = "用户名不能为空",groups = {Add.class,Update.class})
    @ApiModelProperty("用户名,用户名只能由字母、数字、下划线组成，且长度是4-16位")
    private String username;

    @NotBlank(message = "手机号",groups = {Add.class,Update.class})
    private String phone;

    @ApiModelProperty("状态")
    private String status;

    @Pattern(regexp = ".*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$",
    message = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符",groups = {Add.class,Update.class})
    @NotBlank(message = "密码不能为空",groups = {Add.class,Update.class})
    @ApiModelProperty("用户密码,最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符")
    private String password;

    @NotBlank(message = "用户昵称不能为空",groups = {Add.class,Update.class})
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户昵称")
    private String deptId;

    @Pattern(message = "email 格式错误",regexp = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$",groups = {Add.class,Update.class})
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色ID")
    @Size(min = 1,groups = AuthRole.class)
    private Long[] roleIds;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("备注")
    private String remark;

    @NotNull(message = "主键不能为空",groups = {Update.class,AuthRole.class})
    private Long id;


}
