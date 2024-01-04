package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;


/**
 * @ClassName RoleQueryParams
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(name = "角色查询请求参数")
@Data
public class RoleQueryParams extends PageDTO {

    @Serial
    private static final long serialVersionUID = -6377912257907660775L;

    @Schema(name = "角色ID")
    private Long id;

    private String name;

    @Schema(name = "状态 0-正常 1-禁用")
    private String status;

    @Schema(name = "平台类型 0-运营 1-商户 2-商店")
    private Integer platformType;

}
