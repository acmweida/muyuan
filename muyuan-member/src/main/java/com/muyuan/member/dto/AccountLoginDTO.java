package com.muyuan.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录时间")
public class AccountLoginDTO {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 8,max=16,message = "用戶名长度要8~16位之间")
    @ApiModelProperty(value = "用户名")
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(name = "验证码")
    private String code;
}
