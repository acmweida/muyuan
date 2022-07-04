package com.muyuan.product.domains.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.product.domains.model.Brand;
import lombok.Data;

import java.util.Date;


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

    /** 品牌名称 */
    private String name;

    /** 图标 */
    private String logo;

    /** 排序 */
    private Long ordernum;

    /** 英文名称 */
    private String englishName;

    /** 商品数量
 */
    private Long productCount;

    /** 状态  0-上架 1-下架 3-删除 4-禁用 */
    private String status;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 更新人 */
    private String updateBy;

    /** 创建人 */
    private String createBy;

    /** 更吓人ID */
    private Long updateById;

    /** 创建人ID */
    private Long createById;

    /** 审核状态  1-审核中  0-审核通过 2-审核失败 */
    private Long auditStatus;


}
