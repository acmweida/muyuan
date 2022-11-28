package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName SysUserDTO
 * Description 系统用户DTO
 * @Author 2456910384
 * @Date 2022/5/13 13:51
 * @Version 1.0
 */
@Data
@ApiModel("用户信息查询")
public class OperatorQueryParams extends PageDTO {

    @Pattern(regexp = "[a-zA-Z0-9_-]{4,16}$",message = "用户名只能由字母、数字、下划线组成，且长度是4-16位")
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态")
    private String status;

    @NotBlank(message = "平台类型不能为空", groups = {PermissionParams.Add.class, PermissionParams.Update.class})
    @ApiModelProperty(value = "平台类型", required = true)
    private String platformType;

}
