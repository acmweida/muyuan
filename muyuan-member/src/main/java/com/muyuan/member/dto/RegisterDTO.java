package com.muyuan.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel("注册信息请求提")
public class RegisterDTO {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 8,max=16,message = "用戶名长度要8~16位之间")
    @ApiModelProperty(value = "用户名")
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

}
