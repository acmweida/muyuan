package com.muyuan.goods.api.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ${author}
 * @ClassName FeatureQueryRequest
 * Description
 * @date 2022-12-29T16:35:53.035+08:00
 * @Version 1.0
 */
@Data
public class FeatureQueryRequest extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1457932148568l;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * $column.columnComment
     */
    private String parentId;

    /**
     * 是否叶子节点 0-是 1-否
     */
    private Integer leaf;

    /**
     * 状态
     */
    private Long status;

    /**
     * $column.columnComment
     */
    private String creator;

    /**
     * $column.columnComment
     */
    private Long createBy;

    /**
     * $column.columnComment
     */
    private Date createTime;

}
