package com.muyuan.manager.goods.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
public class CategoryParams {

    public interface Add {

    }

    public interface Update {

    }

    @NotNull(message = "主机不能为空",groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "商品分类名称")
    @NotBlank(message = "分类名称不能为空",groups = {Update.class,Add.class})
    private String name;

    @NotBlank(message = "状态 0-上架 1-下架 2-删除",groups = {Update.class,Add.class})
    private Integer status;

    @NotBlank(message = "分类图标不能为空",groups = {Update.class,Add.class})
    @ApiModelProperty(value = "分类图标")
    private String logo;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

}
