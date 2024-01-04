package com.muyuan.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName DictDataDTO
 * Description 字典类型DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@Schema(name = "字典DTO")
@Data
public class DictTypeParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    @Schema(name = "备注")
    private String remark;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空", groups = {Add.class, Update.class})
    @Schema(name = "字典名称")
    private String name;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空", groups = {Add.class, Update.class})
    @Schema(name = "字典类型")
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    @Schema(name = "状态（0正常 1停用）")
    private int status;

    @NotNull(message = "ID不能为空", groups = {Update.class})
    @Schema(name = "ID")
    private Long id;

}
