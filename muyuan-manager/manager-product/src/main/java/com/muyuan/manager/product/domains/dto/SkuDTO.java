package com.muyuan.manager.product.domains.dto;

import com.muyuan.common.bean.BaseDTO;
import com.muyuan.manager.product.domains.model.Sku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName ProductDTO
 * Description 商品信息DTO
 * @Author 2456910384
 * @Date 2021/10/13 14:35
 * @Version 1.0
 */
@Data
public class SkuDTO extends BaseDTO<SkuDTO, Sku> {

    private Long goodsId;

    /**
     * 售价
     */
    @ApiModelProperty(value = "售价")
    @NotNull(message = "SKU价格不能未空")
    private Double price;

    @ApiModelProperty(value = "图片")
    private String pic;

    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    @Min(value = 1,message = "库存数量必须大于0")
    @NotNull(message = "SKU库存不能未空")
    private Integer stock;

    @ApiModelProperty(value = "SKU详情")
    @NotBlank(message = "SKU内容不能为空")
    private String context;

}
