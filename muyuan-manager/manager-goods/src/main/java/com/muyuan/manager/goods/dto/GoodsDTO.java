package com.muyuan.manager.goods.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @ClassName GoodsDTO
 * Description 商品信息DTO
 * @Author 2456910384
 * @Date 2021/10/13 14:35
 * @Version 1.0
 */
@Data
public class GoodsDTO extends PageDTO {

    /**
     * todo:物流模板
     */
    private long wuliouModel;

    /**
     * 商品标题
     */
    @Schema(name = "商品标题")
    @NotNull(message = "商品标题不能为null")
    private String name;

    /**
     * 品牌ID
     */
    @Schema(name = "品牌ID")
    @NotNull(message="品牌ID不能为null")
    private Long brandId;

    /**
     * 分类id
     */
    @Schema(name = "分类id")
    @NotNull(message="分类id不能为null")
    private Long categoryCode;

    /**
     * 店铺ID
     */
    @Schema(name = "店铺ID")
    private Long shopId;

    /**
     * 是否上架
     */
    @Schema(name = "是否上架")
    @NotNull(message="是否上架不能为null")
    private String status;

//    /***
//     * 详情页面
//     */
//    @Schema(value = "详情页面,多个按顺序逗号隔开",example = "/00/g/666apple.jpg,/00/MM/123apple.jpg")
//    @NotNull(message="详情页面不能为null")
//    private String detailUrl;

//    /**
//     * 主图
//     */
//    @Schema(value = "主图,列表显示图",example = "/00/g/666apple.jpg,")
//    @NotNull(message="主图不能为null")
//    private String mainPictureUrl;

    /**
     * 商品标签
     */
    @Schema(name = "商品标签,多个“，”隔开",example = "毛衣,韩版，春季，青年")
    private String tags;

    private List<SkuDTO> skus;
}
