package com.muyuan.manager.goods.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CategoryDTO
 * Description 类目 DTO
 * @Author 2456910384
 * @Date 2022/6/9 15:35
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQueryParams {

    @ApiModelProperty(name = "商品分类名称")
    private String name;

    @ApiModelProperty(name = "状态")
    private Integer status;

    private Long parentId;

    private Integer level;

    private String leaf;

}
