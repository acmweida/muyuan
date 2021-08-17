package com.muyuan.member.base.mybatis;

import com.muyuan.common.jdbc.crud.CrudSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

public interface BaseMapper<T> {


    @SelectProvider(value = CrudSqlProvider.class,method = "selectFirst")
    T selectFirst(Map params);
}
