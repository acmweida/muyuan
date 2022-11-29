package com.muyuan.config.repo.mapper;

import com.muyuan.common.mybatis.jdbc.ConfigBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.config.repo.dataobject.DictTypeDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DictTypeMapper extends ConfigBaseMapper<DictTypeDO> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(DictTypeDO dataObject);
}
