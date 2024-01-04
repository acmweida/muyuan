package com.muyuan.manager.goods.dto;

import com.muyuan.common.bean.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;

/**
 * @ClassName GoodsDTO
 * Description 商品信息DTO
 * @Author 2456910384
 * @Date 2021/10/13 14:35
 * @Version 1.0
 */
@Data
public class SkuDTO extends PageDTO {

    @Serial
    private static final long serialVersionUID = -2262001016076939779L;

    private Long goodsId;

    /**
     * 售价
     */
    @Schema(name = "售价")
    @NotNull(message = "SKU价格不能未空")
    private Double price;

    @Schema(name = "图片")
    private String pic;

    /**
     * 库存
     */
    @Schema(name = "库存")
    @Min(value = 1,message = "库存数量必须大于0")
    @NotNull(message = "SKU库存不能未空")
    private Integer stock;

    @Schema(name = "SKU详情")
    @NotBlank(message = "SKU内容不能为空")
    private String context;

}
