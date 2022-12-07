package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;


/**
 *
 * @author wd
 * @date 2022-07-04T14:16:24.789+08:00
 */
@Data
public class BrandQueryCommand extends PageDTO {

    /** 品牌名称 */
    private String name;

    /** 审核状态  1-审核中  0-审核通过 2-审核魏通过 */
    private Integer auditStatus;

    /** 状态  0-上架 1-下架 3-删除 4-禁用 */
    private Integer status;

    private Long[] categoryCodes;

    private Long categoryCode;


}
