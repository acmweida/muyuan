package com.muyuan.shop.domains.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.common.core.validator.annotions.AtLeastOneNotEmpty;
import com.muyuan.shop.domains.model.Shop;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 商品信息DTO
 */
@Data
@ApiModel(description = "店铺基本信息请求")
@AtLeastOneNotEmpty(fields = {"id","type","name"},message = "店铺ID/类型/名称 需要至少传一项")
public class ShopDTO extends BaseDTO<ShopDTO,Shop> {

    @ApiModelProperty(name = "店铺ID",notes = "店铺ID/店铺编号/类型 需要至少传一项")
    private Long id;


    @ApiModelProperty(name = "店铺类型",notes = "店铺ID/店铺编号/类型 需要至少传一项")
    private Short type;

    @NotBlank(message = "店铺名称不能为空")
    private String name;

    private String logo;

}
