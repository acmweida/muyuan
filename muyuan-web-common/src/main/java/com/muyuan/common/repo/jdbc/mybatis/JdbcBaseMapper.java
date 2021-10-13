package com.muyuan.common.repo.jdbc.mybatis;

import com.muyuan.common.repo.base.id.IdGenerator;
import com.muyuan.common.repo.jdbc.crud.CrudSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface JdbcBaseMapper<T>  {


    @SelectProvider(value = CrudSqlProvider.class,method = "selectOne")
    T selectOne(Map params);

    @SelectProvider(value = CrudSqlProvider.class,method = "selectList")
    List<T> selectList(Map params);

    @IdGenerator()
    @SelectProvider(value = CrudSqlProvider.class,method = "insert")
    int insert(T dataObject);
}
