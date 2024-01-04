package com.muyuan.system.dto;

import com.muyuan.common.core.constant.GlobalConst;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @ClassName DeptParams
 * Description DeptParams
 * @Author 2456910384
 * @Date 2022/11/28 16:00
 * @Version 1.0
 */
@Data
@Schema(name = "部门")
public class DeptParams {

    private Long id;

    @NotBlank(message = "部门名称不能为空")
    @Schema(name = "部门名称")
    private String name;

    @Schema(name ="状态")
    private String status;

    @Schema(name ="上级部门ID")
    private Long parentId;

    @NotNull(message = "排序号不能为空")
    private Integer orderNum;

    @Schema(name ="负责人")
    private String leader;

    @Schema(name ="电话")
    private String phone;

    @Pattern(message = "email 不能为空", regexp = GlobalConst.DEFAULT_EMAIL_REGEX)
    private String email;

}
