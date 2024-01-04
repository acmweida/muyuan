package com.muyuan.store.shop.domains.dto;

import com.muyuan.common.bean.PageDTO;
import com.muyuan.common.core.validator.annotions.AtLeastOneNotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;


/**
 * 商品信息DTO
 */
@Data
@Schema(description = "店铺基本信息请求")
@AtLeastOneNotEmpty(fields = {"id","type","name"},message = "店铺ID/类型/名称 需要至少传一项")
public class ShopDTO extends PageDTO implements Serializable {

    @Schema(name = "店铺ID",description = "店铺ID/店铺编号/类型 需要至少传一项")
    private Long id;


    @Schema(name = "店铺类型",description = "店铺ID/店铺编号/类型 需要至少传一项")
    private Short type;

    @NotBlank(message = "店铺名称不能为空")
    private String name;

    private String logo;

}
