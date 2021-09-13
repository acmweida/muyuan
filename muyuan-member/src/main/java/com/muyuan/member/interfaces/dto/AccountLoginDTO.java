package com.muyuan.member.interfaces.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录时间")
public class AccountLoginDTO {

    @NotBlank(message = "账号不能为空")
    @Length(min = 8,max=16,message = "用戶名长度要8~16位之间")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(name = "验证码")
    private String code;

    @Range(min = 1,max = 2,message = "用户类型无效")
    @ApiModelProperty(value = "用户类型 1-会员 2=商家")
    private short type;

    @NotBlank(message = "验证码校验编号不能为空")
    @ApiModelProperty(name = "验证码校验编号")
    private String uuid;
}
