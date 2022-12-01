package com.muyuan.system.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DictDataVO {

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

    private Date updateTime;

    private String updater;

    private Long updateBy;

    private String remark;
}
