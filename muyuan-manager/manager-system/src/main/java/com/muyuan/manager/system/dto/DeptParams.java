package com.muyuan.manager.system.dto;

import com.muyuan.common.core.constant.GlobalConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName DeptParams
 * Description DeptParams
 * @Author 2456910384
 * @Date 2022/11/28 16:00
 * @Version 1.0
 */
@Data
@ApiModel("部门")
public class DeptParams {

    @NotBlank(message = "部门名称不能为空")
    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("上级部门ID")
    private Long parentId;

    @NotNull(message = "排序号不能为空")
    private Integer orderNum;

    @ApiModelProperty("负责人")
    private String leader;

    @ApiModelProperty("电话")
    private String phone;

    @Pattern(message = "email 不能为空",regexp = GlobalConst.DEFAULT_EMAIL_REGEX)
    private String email;

    private Long id;
}
