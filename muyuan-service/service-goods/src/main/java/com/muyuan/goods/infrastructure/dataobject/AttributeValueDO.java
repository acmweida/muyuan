package com.muyuan.goods.infrastructure.dataobject;

import lombok.Data;


/**
 * 类目属性值对象 t_attribute_value
 *
 * @author ${author}
 * @date 2022-12-27T11:15:54.529+08:00
 */
@Data
public class AttributeValueDO  {

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long attributeId;

    /** $column.columnComment */
    private String value;


}
