package com.muyuan.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录时间")
public class AccountLoginDTO {

    @ApiModelProperty(value = "用户名")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

}
