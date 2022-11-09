package com.muyuan.config.entity;

import lombok.Data;

import java.util.Date;

/**
 * 字典数据
 */
@Data
public class DictData {

    private Long id;

    private int orderNum;

    private String label;

    private String value;

    private String type;

    private String cssClass;

    private String listClass;

    private int def;

    private int status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String remark;

    public DictData() {
    }

    public DictData(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public DictData(Long id, String value, String type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }
}
