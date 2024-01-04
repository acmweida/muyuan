package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @ClassName SysMenuDTO
 * Description 菜单DTO
 * @Author 2456910384
 * @Date 2022/4/15 13:55
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(name = "菜单查询参数")
@Builder
@AllArgsConstructor
@Data
public class MenuQueryParams  extends PageDTO {

    @Serial
    private static final long serialVersionUID = -2181386175460120637L;

    @Schema(name = "id")
    private Long id;

    /**
     * 菜单名称
     */
    @Schema(name = "菜单名称")
    private String name;

    /**
     * 父菜单ID
     */
    @Schema(name = "父菜单ID")
    private Long parentId;


    /**
     * 菜单状态（0正常 1停用）
     */
    @Schema(name = "状态 0正常 1停用")
    private String status;

    @Schema(name = "平台类型")
    private Integer platformType;

}
