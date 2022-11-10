package com.muyuan.manager.system.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName SysMenuDTO
 * Description 菜单DTO
 * @Author 2456910384
 * @Date 2022/4/15 13:55
 * @Version 1.0
 */
@ApiModel("菜单查询参数")
@Builder
@AllArgsConstructor
@Data
public class MenuQueryParams  extends PageDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;


    /**
     * 菜单状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态 0正常 1停用")
    private String status;

    @ApiModelProperty(value = "平台类型")
    private Integer platformType;

}
