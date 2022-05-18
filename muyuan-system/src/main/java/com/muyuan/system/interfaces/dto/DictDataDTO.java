package com.muyuan.system.interfaces.dto;

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
public class DictDataDTO {

    @ApiModelProperty(value = "排序,默认值:0")
    private int orderNum;

    @NotBlank(message = "字典标签不能为空")
    @ApiModelProperty(value = "字典标签")
    private String label;

    @NotBlank(message = "字典值不能为空")
    @ApiModelProperty(value = "字典值")
    private String value;

    @NotBlank(message = "字典值不能为空")
    @ApiModelProperty(value = "字典类型编码")
    private String type;

    @ApiModelProperty(value = "样式属性")
    private String cssClass;

    @ApiModelProperty(value = "表格回显样式")
    private String listClass;

    @ApiModelProperty(value = "备注")
    private String remark;

    private int pageNum = 1;

    private int pageSize = 10;

    private String status;
}
