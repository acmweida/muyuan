package com.muyuan.manager.goods.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


/**
 *
 * @author wd
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandQueryParams extends PageDTO {

    private Long id;

    /** 品牌名称 */
    private String name;

    /** 审核状态  1-审核中  0-审核通过 2-审核魏通过 */
    @Range(message = "认证状态码输入错误",min = 0,max = 2)
    private Integer auditStatus;

    /** 状态  0-上架 1-下架 3-删除 4-禁用 */
    @Range(message = "状态码输入错误",min = 0,max = 4)
    private Integer status;

    private Long[] categoryCodes;

    private Long categoryCode;


}
