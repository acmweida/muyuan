package com.muyuan.config.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.UserBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.config.infrastructure.repo.dataobject.DictDataDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DictDataMapper extends UserBaseMapper<DictDataDO> {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insertAuto(DictDataDO dataObject);
}
