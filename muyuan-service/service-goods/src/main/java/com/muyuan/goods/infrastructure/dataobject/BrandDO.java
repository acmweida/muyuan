package com.muyuan.goods.infrastructure.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class BrandDO {

    /**  */
    private Long id;

    /** 品牌名称 */
    private String name;

    /** 图标 */
    private String logo;

    /** 排序 */
    private Integer orderNum;

    /** 英文名称 */
    private String englishName;

    /** 状态  0-上架 1-下架 2-删除 3-禁用 */
    private Integer status;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 更新人 */
    private String updater;

    /** 创建人 */
    private String creator;

    /** 更吓人ID */
    private Long updateBy;

    /** 创建人ID */
    private Long createBy;

    /** 审核状态  1-审核中  0-审核通过 2-审核失败 */
    private Integer auditStatus;

}
