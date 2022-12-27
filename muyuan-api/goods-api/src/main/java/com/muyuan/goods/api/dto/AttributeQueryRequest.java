package com.muyuan.goods.api.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ${author}
 * @ClassName AttributeQueryRequest
 * Description
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeQueryRequest extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1457932148568l;

    /**
     * $column.columnComment
     */
    private Long id;


    /**
     * $column.columnComment
     */
    private String name;

    /**
     * $column.columnComment
     */
    private Long categoryCode;

    /**
     * $column.columnComment
     */
    private Long type;

    /**
     * $column.columnComment
     */
    private String code;

    /**
     * $column.columnComment
     */
    private Long inputType;

    /**
     * $column.columnComment
     */
    private Long htmlType;

    /**
     * $column.columnComment
     */
    private Long status;

    /**
     * $column.columnComment
     */
    private Date createTime;

    /**
     * $column.columnComment
     */
    private Date updateTime;

    /**
     * $column.columnComment
     */
    private Long createBy;

    /**
     * $column.columnComment
     */
    private String creator;

    /**
     * $column.columnComment
     */
    private Long updateBy;

    /**
     * $column.columnComment
     */
    private String updater;

}
