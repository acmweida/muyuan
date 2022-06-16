package com.muyuan.common.dao;

import com.muyuan.common.base.config.mybatis.CommonBaseMapper;
import com.muyuan.common.model.File;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface FileMapper extends CommonBaseMapper<File> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(File dataObject);
}
