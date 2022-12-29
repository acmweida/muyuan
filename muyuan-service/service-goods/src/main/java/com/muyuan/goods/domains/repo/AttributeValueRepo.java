package com.muyuan.goods.domains.repo;


import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.AttributeValue;
import com.muyuan.goods.face.dto.AttributeValueQueryCommand;

import java.util.List;

/**
 * 类目属性值对象 t_attribute_value
 *
 * @author ${author}
 * @date 2022-12-27T11:15:54.529+08:00
 */

public interface AttributeValueRepo {

    Page<AttributeValue> select(AttributeValueQueryCommand command);

    boolean batchInsert(List<AttributeValue> attributeValues);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<AttributeValue> deleteBy(Long... ids);

}
