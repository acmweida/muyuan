package com.muyuan.goods.infrastructure.mapper;

import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.goods.infrastructure.dataobject.CategoryDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 商品分类Mapper接口
 *
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 */
public interface CategoryMapper extends GoodsBaseMapper<CategoryDO> {

    String LEVEL = "level";
    String CODE = "code";
    String ANCESTORS = "ancestors";
    String STATUS = "status";
    String LEAF = "leaf";

    Object[] STATUS_OK = new String[]{"0","1"};


    List<CategoryDO> selectByBrandId(Long... brandIds);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(CategoryDO dataObject);

}
