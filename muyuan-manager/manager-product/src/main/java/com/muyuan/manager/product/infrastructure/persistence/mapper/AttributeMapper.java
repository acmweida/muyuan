package com.muyuan.manager.product.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.product.domains.model.Attribute;
import com.muyuan.manager.product.infrastructure.config.mybatis.ProductBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 商品分类属性Mapper接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Mapper
public interface AttributeMapper extends ProductBaseMapper<Attribute> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(Attribute dataObject);

}
