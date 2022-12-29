package com.muyuan.manager.goods.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
public class AttributeParams {

    public interface Add {

    }

    public interface Update {

    }

    public interface ValuesUpdate {

    }

    /**  */
    @NotNull(message = "主键ID不能为空",groups = {Update.class,ValuesUpdate.class})
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 属性名称 */
    @NotBlank(message = "属性名称不能为空",groups = {Add.class})
    @ApiModelProperty(value = "名称")
    private String name;

    @NotBlank(message = "属性编码不能为空",groups = {Add.class})
    @ApiModelProperty(value = "属性Code")
    private String code;

    @NotNull(message = "categoryCode 分类Id不能为空",groups = {Add.class})
    /** 商品分类ID */
    @ApiModelProperty(value = "商品分类Code")
    private Long categoryCode;

    @NotNull(message = "属性类型不能为空",groups = {Add.class})
    /** 属性类型 1:关键属性 2:销售属性 3:非关键属性 */
    @ApiModelProperty(value = " 属性类型 转换为二进制 1:公共属性 10:销售属性 100:关键属性 1000:非关键属性 type值为类型的和")
    private Integer type;

    @NotNull(message = "取值类型不能为空",groups = {Add.class})
    @ApiModelProperty(value = "取值类型")
    private Integer inputType;

    private Long valueReference;

    @NotNull(message = "输入类型不能为空",groups = {Add.class})
    @ApiModelProperty(value = "取值类型")
    private Integer valueType;

    @NotNull(message = "可选值不能为空",groups = {ValuesUpdate.class})
    @ApiModelProperty(value = "属性值,当valueType=1时，可对该值更新")
    private String[] values;

}
