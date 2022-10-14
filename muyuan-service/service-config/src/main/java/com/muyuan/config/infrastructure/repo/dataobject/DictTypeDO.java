package com.muyuan.config.infrastructure.repo.dataobject;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 字典类型
 */
@Data
public class DictTypeDO {


    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    private int status;

    private Date createTime;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;

    private String remark;

    private List<DictDataDO> dictDataDOList;

    public DictTypeDO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public DictTypeDO() {
    }
}
