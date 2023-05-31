package com.muyuan.goods.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${author}
 * @ClassName AttributeValueQueryRequest
 * Description
 * @date 2022-12-27T11:15:54.529+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class AttributeValueQueryCommand extends PageDTO {


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
