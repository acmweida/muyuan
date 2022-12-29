package com.muyuan.goods.api.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 类目属性值对象 t_attribute_value
 *
 * @author ${author}
 * @date 2022-12-27T11:15:54.529+08:00
 */
@Data
public class AttributeValueDTO implements Serializable {


    private static final long serialVersionUID = -7363721091396212004L;
    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * $column.columnComment
     */
    private Long attributeId;

    /**
     * $column.columnComment
     */
    private String value;


}
