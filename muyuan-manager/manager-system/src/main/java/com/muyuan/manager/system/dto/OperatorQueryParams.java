package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

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

    public interface SelectAllow {

    }

    @ApiModelProperty(value = "角色名称",required = true)
    @NotNull(message = "角色ID不能为空",groups = SelectAllow.class)
    private Long roleId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "平台类型", required = true)
    private String platformType;

}
