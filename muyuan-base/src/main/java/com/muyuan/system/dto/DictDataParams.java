package com.muyuan.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName DictDataDTO
 * Description 字典值DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@Schema(name = "字典DTO")
@Data
public class DictDataParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    @Schema(name = "排序,默认值:0")
    private int orderNum;

    @NotBlank(message = "字典标签不能为空", groups = {Add.class, Update.class})
    @Schema(name = "字典标签")
    private String label;

    @NotBlank(message = "字典值不能为空", groups = {Add.class, Update.class})
    @Schema(name = "字典值")
    private String value;

    @NotBlank(message = "字典类型不能为空", groups = {Add.class, Update.class})
    @Schema(name = "字典类型编码")
    private String type;

    @Schema(name = "样式属性")
    private String cssClass;

    @Schema(name = "表格回显样式")
    private String listClass;

    @Schema(name = "备注")
    private String remark;

    @Schema(name = "ID")
    @NotNull(groups = {Update.class})
    private Long id;

    @Schema(name = "状态 默认 0-正常 1-禁用 默认0")
    private String status = "0";

    public DictDataParams() {
    }

    public DictDataParams(Long id) {
        this.id = id;
    }
}
