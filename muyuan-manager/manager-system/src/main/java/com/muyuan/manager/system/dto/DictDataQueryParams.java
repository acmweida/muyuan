package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName DictDataDTO
 * Description 字典值DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@ApiModel("字典DTO")
@Data
public class DictDataQueryParams extends PageDTO {

    @ApiModelProperty(value = "字典标签")
    private String label;

    @NotBlank(message = "字典值不能为空")
    @ApiModelProperty(value = "字典类型编码")
    private String type;

    private String status;

}
