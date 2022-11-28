package com.muyuan.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SysDeptDTO
 * Description SysDeptDTO 部门DTO
 * @Author 2456910384
 * @Date 2022/5/13 10:46
 * @Version 1.0
 */
@Data
@ApiModel("部门查询参数")
public class DeptQueryParams {

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("状态")
    private String status;
}
