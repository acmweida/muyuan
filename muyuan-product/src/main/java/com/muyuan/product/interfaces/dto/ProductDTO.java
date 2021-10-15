package com.muyuan.product.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName ProductDTO
 * Description 商品信息DTO
 * @Author 2456910384
 * @Date 2021/10/13 14:35
 * @Version 1.0
 */
@Data
@ApiModel("商品信息DTO")
public class ProductDTO {

    private String token;

    /**
     * todo:物流模板
     */
    private long wuliouModel;

    /**
     * 商品标题
     */
    @ApiModelProperty("商品标题")
    @NotNull(message = "商品标题不能为null")
    private String title;

    /**
     * 品牌ID
     */
    @ApiModelProperty("品牌ID")
    @NotNull(message="品牌ID不能为null")
    private Long brandId;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    @NotNull(message="分类id不能为null")
    private Long categoryId;

    /**
     * 店铺ID
     */
    @ApiModelProperty("店铺ID")
    @NotNull(message="店铺ID不能为null")
    private Long shopId;

    /**
     * 是否上架
     */
    @ApiModelProperty(value = "是否上架")
    @NotNull(message="是否上架不能为null")
    private Boolean publish;

    /***
     * 详情页面
     */
    @ApiModelProperty(value = "详情页面,多个按顺序逗号隔开",example = "/00/g/666apple.jpg,/00/MM/123apple.jpg")
    @NotNull(message="详情页面不能为null")
    private String detailUrl;

    /**
     * 主图
     */
    @ApiModelProperty(value = "主图,列表显示图",example = "/00/g/666apple.jpg,")
    @NotNull(message="主图不能为null")
    private String mainPictureUrl;

    /**
     * 商品标签
     */
    @ApiModelProperty(value = "商品标签,多个“，”隔开",example = "毛衣,韩版，春季，青年")
    private String tags;
}
