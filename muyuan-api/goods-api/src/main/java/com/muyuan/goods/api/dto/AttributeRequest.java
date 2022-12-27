package com.muyuan.goods.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ${author}
 * @ClassName AttributeRequest
 * Description
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class AttributeRequest extends OptRequest implements Serializable {

    @Builder
    public AttributeRequest(Opt opt, Long id, String name, Long categoryCode, Integer type, String code, Integer inputType, Integer htmlType, Long valueReference, Integer valueType) {
        super(opt);
        this.id = id;
        this.name = name;
        this.categoryCode = categoryCode;
        this.type = type;
        this.code = code;
        this.inputType = inputType;
        this.htmlType = htmlType;
        this.valueReference = valueReference;
        this.valueType = valueType;
    }

    private static final long serialVersionUID = -6515124582723029455L;

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

    /**
     * $column.columnComment
     */
    private Long valueReference;

    private Integer valueType;

}
