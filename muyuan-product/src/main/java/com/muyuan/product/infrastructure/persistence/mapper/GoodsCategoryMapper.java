package com.muyuan.product.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.infrastructure.config.mybatis.ProductBaseMapper;
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
public interface GoodsCategoryMapper extends ProductBaseMapper<GoodsCategory> {

    String LEVEL = "level";

    String NAME = "name";

    String CODE = "code";

    String PARENT_ID = "parentId";

    String LEAF = "leaf";

    String STATUS_DELETE = "2";

    Object[] STATUS_OK = new String[]{"0","1"};


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(GoodsCategory dataObject);
}
