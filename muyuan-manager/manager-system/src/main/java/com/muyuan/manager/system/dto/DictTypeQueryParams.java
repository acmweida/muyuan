package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @ClassName DictDataDTO
 * Description 字典类型DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@ApiModel("字典DTO")
@Data
public class DictTypeQueryParams extends PageDTO {

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @ApiModelProperty(value = "字典名称", required = true)
    private String name;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @ApiModelProperty(value = "字典类型", required = true)
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private int status;

}
