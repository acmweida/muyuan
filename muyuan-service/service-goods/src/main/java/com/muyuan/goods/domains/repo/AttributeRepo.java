package com.muyuan.goods.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.face.dto.AttributeQueryCommand;

/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */

public interface AttributeRepo {

    Page<Attribute> select(AttributeQueryCommand command);


    void delete(Long... ids);


}
