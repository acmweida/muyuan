package com.muyuan.system.interfaces.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.system.domain.model.SysDept;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName SysDeptDTO
 * Description SysDeptDTO 部门DTO
 * @Author 2456910384
 * @Date 2022/5/13 10:46
 * @Version 1.0
 */
@Data
public class SysDeptDTO extends BaseDTO<SysDeptDTO, SysDept> {

    @NotBlank(message = "部门名称不能为空")
    private String name;

    private String status;

    private Long parentId;

    @NotNull(message = "排序号不能为空")
    private Integer orderNum;

    private String leader;

    private String phone;

    @Pattern(message = "email 不能为空",regexp = GlobalConst.DEFAULT_EMAIL_REGEX)
    private String email;

    private Long id;

}
