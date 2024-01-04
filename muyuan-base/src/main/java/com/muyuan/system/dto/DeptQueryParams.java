package com.muyuan.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName SysDeptDTO
 * Description SysDeptDTO 部门DTO
 * @Author 2456910384
 * @Date 2022/5/13 10:46
 * @Version 1.0
 */
@Data
@Schema(name = "部门查询参数")
public class DeptQueryParams {

    @Schema(name ="部门名称")
    private String name;

    @Schema(name = "状态")
    private String status;
}
