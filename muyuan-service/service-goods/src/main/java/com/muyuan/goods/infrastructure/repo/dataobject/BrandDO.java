package com.muyuan.goods.infrastructure.repo.dataobject;

import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;

@Data
public class BrandDO extends BaseDO {

    /** 品牌名称 */
    private String name;

    /** 图标 */
    private String logo;

    /** 排序 */
    private Integer orderNum;

    /** 英文名称 */
    private String englishName;

    /** 状态  0-上架 1-下架 2-禁用 3-禁用 */
    private Integer status;

    /** 0-否 1-是 */
    private Integer delFlag;

    /** 备注 */
    private String remark;

    /** 审核状态  1-审核中  0-审核通过 2-审核失败 */
    private Integer auditStatus;

}
