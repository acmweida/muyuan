package com.muyuan.product.domains.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.product.domains.model.Brand;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;


/**
 * 品牌对象 t_brand
 *
 * @author ${author}
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Data
public class BrandDTO extends BaseDTO<BrandDTO,Brand> {

    /**  */
    private Long id;

    @NotBlank(message = "品牌名称不能为空")
    /** 品牌名称 */
    private String name;

    @NotBlank(message = "图标不能为空")
    /** 图标 */
    private String logo;

    /** 排序 */
    private Integer orderNum = 0;

    /** 英文名称 */
    private String englishName;

    /** 备注 */
    private String remark;

    /** 审核状态  1-审核中  0-审核通过 2-审核魏通过 */
    @Range(message = "认证状态码输入错误",min = 0,max = 2)
    private Integer auditStatus;

    /** 状态  0-上架 1-下架 3-删除 4-禁用 */
    @Range(message = "状态码输入错误",min = 0,max = 4)
    private Integer status;


}
