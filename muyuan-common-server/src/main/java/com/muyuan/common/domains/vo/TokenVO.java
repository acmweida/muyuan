package com.muyuan.common.domains.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName TokenVO
 * Description 请求Token校验信息
 * @Author 2456910384
 * @Date 2021/10/14 10:01
 * @Version 1.0
 */
@Data
@ApiModel("token包装")
public class TokenVO {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("过期时间")
    private Date expireTime;

}
