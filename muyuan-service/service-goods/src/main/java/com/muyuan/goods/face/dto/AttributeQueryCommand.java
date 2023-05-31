package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${author}
 * @ClassName AttributeQueryRequest
 * Description
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class AttributeQueryCommand extends PageDTO {


    @Builder
    public AttributeQueryCommand(Integer pageNum, Integer pageSize, Long id, String name, Long categoryCode, Long type, String code, Long inputType, Long htmlType, Long status) {
        super(pageNum, pageSize);
        this.id = id;
        this.name = name;
        this.categoryCode = categoryCode;
        this.type = type;
        this.code = code;
        this.inputType = inputType;
        this.htmlType = htmlType;
        this.status = status;
    }

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

}
