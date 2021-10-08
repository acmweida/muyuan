package com.muyuan.product.interfaces.dto;

import com.muyuan.common.validator.annotions.AtLeastOneNotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("商店商品查询请求")
public class ShopProductDTO {

    @ApiModelProperty(name = "shopId",value = "店铺ID",required = true)
    @NotEmpty(message = "店铺ID必填")
    private Long shopId;

    @ApiModelProperty(name = "商品分类ID")
    private Long categoryId;

}