package com.muyuan.manager.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DictDataMapper extends SystemBaseMapper<DictData> {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insertAuto(DictData dataObject);
}
