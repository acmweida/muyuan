package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 商品分类属性Mapper接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Mapper
public interface CategoryAttributeMapper extends ProductBaseMapper<CategoryAttribute> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(CategoryAttribute dataObject);

}
