package com.muyuan.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@ApiModel("注册信息请求提")
@Data
public class RegisterDTO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @NotBlank(message = "账号名不能为空")
    @Length(min = 8,max=16,message = "用戶名长度要8~16位之间")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @Range(min = 0,max = 1,message = "用户类型无效")
    @NotBlank(message = "用户类型不能为空")
    @ApiModelProperty(value = "用户类型 0-会员 1=商家")
    private short type;

}
