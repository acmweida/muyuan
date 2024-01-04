package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName DictDataDTO
 * Description 字典类型DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(name = "字典DTO")
@Data
public class DictTypeQueryParams extends PageDTO {

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Schema(name = "字典名称")
    private String name;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Schema(name = "字典类型")
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    @Schema(name = "状态（0正常 1停用）")
    private int status;

}
