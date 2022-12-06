package com.muyuan.manager.goods.base.persistence.mapper;


import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.goods.domains.model.Category;
import com.muyuan.manager.goods.domains.model.Feature;
import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 通用特征量Mapper接口
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@Mapper
public interface FeatureMapper extends GoodsBaseMapper<Feature> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(Category dataObject);

}
