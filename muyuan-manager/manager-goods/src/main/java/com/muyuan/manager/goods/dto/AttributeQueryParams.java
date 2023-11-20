package com.muyuan.manager.goods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeQueryParams {

    /**  */
    private Long id;

    /** 属性名称 */
    @NotBlank(message = "属性名称不能为空")
    private String name;

    @NotBlank(message = "属性编码不能为空")
    private String code;

    @NotNull(message = "categoryCode 分类Id不能为空")
    /** 商品分类ID */
    private Long categoryCode;

    @NotNull(message = "属性类型不能为空")
    /** 属性类型 1:关键属性 2:销售属性 3:非关键属性 */
    private Integer type;

    @NotNull(message = "取值类型不能为空")
    private Integer inputType;

    private Long valueReference;

    private Integer valueType;

}
