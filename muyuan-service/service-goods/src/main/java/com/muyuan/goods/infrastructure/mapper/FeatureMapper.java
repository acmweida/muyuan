package com.muyuan.goods.infrastructure.mapper;

import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.goods.infrastructure.dataobject.FeatureDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 通用特征量Mapper接口
 *
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 */
public interface FeatureMapper extends GoodsBaseMapper<FeatureDO> {


    String ID = "id";
    String NAME = "name";
    String PARENT_ID = "parentId";
    String LEAF = "leaf";
    String STATUS = "status";
    String CREATOR = "creator";
    String CREATE_BY = "createBy";
    String CREATE_TIME = "createTime";

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(FeatureDO dataObject);

}
