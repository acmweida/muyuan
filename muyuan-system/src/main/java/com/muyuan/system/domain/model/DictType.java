package com.muyuan.system.domain.model;

import com.muyuan.common.mybatis.id.AutoIncrement;
import lombok.Data;

import java.util.Date;

/**
 * 字典类型
 */
@Data
public class DictType {


    @AutoIncrement
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
