package com.muyuan.manager.goods.base.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.goods.domains.model.Category;
import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @ClassName GoodsCategoryMapper 接口
 * Description 商品分类 Mapper
 * @Author 2456910384
 * @Date 2022/6/10 11:39
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper extends GoodsBaseMapper<Category> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(Category dataObject);
}
