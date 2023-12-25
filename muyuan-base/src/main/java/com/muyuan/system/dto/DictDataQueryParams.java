package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class DictDataQueryParams extends PageDTO {

    @Schema(name = "字典标签")
    private String label;

    @NotBlank(message = "字典值不能为空")
    @Schema(name = "字典类型编码")
    private String type;

    private String status;

}
