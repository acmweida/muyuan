package com.muyuan.system.application.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DictDataVO {

    private Long id;

    private int sort;

    private String label;

    private String value;

    private String type;

    private String cssClass;

    private String listClass;

    private int def;

    private int status;

    private String createBy;

    private Date createTime;

    private String updteBy;

    private Date updateBy;

    private String remark;
}
