package com.muyuan.manager.product.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.product.domains.model.Category;
import com.muyuan.manager.product.infrastructure.config.mybatis.ProductBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @ClassName ProductCategoryMapper 接口
 * Description 商品分类 Mapper
 * @Author 2456910384
 * @Date 2022/6/10 11:39
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper extends ProductBaseMapper<Category> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(Category dataObject);
}
