package com.muyuan.common.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName RepeatableReq
 * Description 幂等性
 * @Author 2456910384
 * @Date 2021/10/14 9:51
 * @Version 1.0
 */
public class RepeatableRequest {

    @ApiModelProperty("该接口需要请求唯一token,请先获取token")
    @NotNull(message = "token不能为null")
    private String token;
}
