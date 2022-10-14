package com.muyuan.config.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DictDataDTO
 * Description DictDataDTO
 * @Author 2456910384
 * @Date 2022/10/14 10:22
 * @Version 1.0
 */
@Data
public class DictDataDTO implements Serializable {

    private static final long serialVersionUID = 1457832158568l;

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

    private String updateBy;

    private Date updateTime;

    private String remark;

}
