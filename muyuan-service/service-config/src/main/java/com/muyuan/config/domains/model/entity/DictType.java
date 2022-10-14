package com.muyuan.config.domains.model.entity;

import com.muyuan.config.infrastructure.repo.dataobject.DictDataDO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 字典类型
 */
@Data
public class DictType {


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

}
