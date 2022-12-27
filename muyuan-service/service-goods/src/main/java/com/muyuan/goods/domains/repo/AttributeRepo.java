package com.muyuan.goods.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.face.dto.AttributeQueryCommand;

import java.util.List;

/**
 * 类目属性对象 t_attribute
 *
 * @author ${author}
 * @date 2022-12-26T17:20:39.753+08:00
 */

public interface AttributeRepo {

    Page<Attribute> select(AttributeQueryCommand command);

    Attribute selectAttribute(Long id);

    Attribute selectAttribute(Attribute.Identify identify);

    boolean addAttribute(Attribute attribute);

    /**
     * 更新信息
     * @param attribute
     * @return old value
     */
    Attribute updateAttribute(Attribute attribute);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<Attribute> deleteBy(Long... ids);

}
