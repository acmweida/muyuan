package com.muyuan.shop.interfaces.dto;

import com.muyuan.common.validator.annotions.AtLeastOneNotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 商品信息DTO
 */
@Data
@ApiModel(description = "店铺基本信息请求")
@AtLeastOneNotEmpty(fields = {"id","shopNo","type"},message = "店铺ID/店铺编号/类型 需要至少传一项")
public class ShopDTO {

    @ApiModelProperty(name = "店铺ID",notes = "店铺ID/店铺编号/类型 需要至少传一项")
    private Long id;

    /**
     * 店铺编号
     */
    @ApiModelProperty(name = "店铺编号",notes = "店铺ID/店铺编号/类型 需要至少传一项")
    private String shopNo;

    @ApiModelProperty(name = "店铺类型",notes = "店铺ID/店铺编号/类型 需要至少传一项")
    private Short type;

}
