package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName RoleQueryParams
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@ApiModel("角色查询请求参数")
@Data
public class RoleQueryParams extends PageDTO {

    @ApiModelProperty(value = "角色ID")
    private Long id;

    private String name;

    @ApiModelProperty(value = "状态 0-正常 1-禁用")
    private String status;

    @ApiModelProperty(value = "平台类型 0-运营 1-商户 2-商店")
    private Integer platformType;

}
