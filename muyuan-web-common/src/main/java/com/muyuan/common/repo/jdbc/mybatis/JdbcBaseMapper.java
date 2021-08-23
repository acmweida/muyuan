package com.muyuan.common.repo.jdbc.mybatis;

import com.muyuan.common.repo.base.id.IdGenerator;
import com.muyuan.common.repo.jdbc.crud.CrudSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

public interface JdbcBaseMapper<T>  {


    @SelectProvider(value = CrudSqlProvider.class,method = "selectFirst")
    T selectFirst(Map params);

    @IdGenerator(fieldName = "id")
    @SelectProvider(value = CrudSqlProvider.class,method = "insert")
    int insert(T dataObject);
}
