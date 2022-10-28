package com.muyuan.config.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictDataRequest implements Serializable {

    private static final long serialVersionUID = 1057932158568l;

    /**
     * 排序,默认值:0
     */
    private int orderNum;

    @NotBlank(message = "字典标签不能为空")
    /**
     * 字典标签
     */
    private String label;

    @NotBlank(message = "字典值不能为空")
    /**
     * 字典值
     */
    private String value;

    @NotBlank(message = "字典类型不能为空")
    /**
     * 字典类型编码
     */
    private String type;

    /**
     * 样式属性
     */
    private String cssClass;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 默认 0-正常 1-禁用 默认0
     */
    private String status = "0";


    private Long createBy;
}
