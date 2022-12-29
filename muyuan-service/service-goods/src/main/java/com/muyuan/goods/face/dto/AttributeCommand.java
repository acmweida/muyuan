package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.OptCommand;
import lombok.Data;

/**
 * @author ${author}
 * @ClassName AttributeRequest
 * Description
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Data
public class AttributeCommand extends OptCommand {

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
    private Integer type;

    /**
     * $column.columnComment
     */
    private String code;

    /**
     * $column.columnComment
     */
    private Integer inputType;

    /**
     * $column.columnComment
     */
    private Integer htmlType;

    private Long valueReference;

    private Integer valueType;

    private String[] values;

}
