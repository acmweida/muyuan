package com.muyuan.config.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DictDataRequest extends OptRequest implements Serializable {

    @Builder
    public DictDataRequest(Opt opt, Long id, int orderNum, String label, String value, String type, String cssClass, String listClass, String remark, String status) {
        super(opt);
        this.id = id;
        this.orderNum = orderNum;
        this.label = label;
        this.value = value;
        this.type = type;
        this.cssClass = cssClass;
        this.listClass = listClass;
        this.remark = remark;
        this.status = status;
    }

    private static final long serialVersionUID = 1057932158568l;

    private Long id;

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

}
