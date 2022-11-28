package com.muyuan.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName SysDeptDTO
 * Description SysDeptDTO 部门DTO
 * @Author 2456910384
 * @Date 2022/5/13 10:46
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptQueryRequest implements Serializable {

    private static final long serialVersionUID = 1557932248501l;

    private String name;

    private String status;
}
