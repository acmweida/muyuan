package com.muyuan.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @ClassName DictDataDTO
 * Description 字典值DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@ApiModel("字典DTO")
@Data
public class DictDataParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    @ApiModelProperty(value = "排序,默认值:0")
    private int orderNum;

    @NotBlank(message = "字典标签不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "字典标签", required = true)
    private String label;

    @NotBlank(message = "字典值不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "字典值", required = true)
    private String value;

    @NotBlank(message = "字典类型不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty(value = "字典类型编码", required = true)
    private String type;

    @ApiModelProperty(value = "样式属性")
    private String cssClass;

    @ApiModelProperty(value = "表格回显样式")
    private String listClass;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "ID", required = true)
    @NotNull(groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "状态 默认 0-正常 1-禁用 默认0")
    private String status = "0";

    public DictDataParams() {
    }

    public DictDataParams(Long id) {
        this.id = id;
    }
}
