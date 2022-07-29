package com.muyuan.product.domains.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.product.domains.model.Sku;
import io.swagger.annotations.ApiModel;
import lombok.Data;

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
@ApiModel("SKU DTO")
public class SkuDTO extends BaseDTO<SkuDTO, Sku> {

    @NotBlank(message = "商品ID不能未空")
    private Long goodsId;

    /**
     * 售价
     */
    @NotNull(message = "SKU价格不能未空")
    private Double price;

    private String pic;

    /**
     * 库存
     */
    @NotNull(message = "SKU库存不能未空")
    private Integer stock;

    @NotBlank(message = "SKU内容不能为空")
    private String context;

}
