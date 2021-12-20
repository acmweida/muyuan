package com.muyuan.product.infrastructure.config.mybatis;

import com.muyuan.common.repo.base.id.IdGenerator;
import com.muyuan.common.repo.jdbc.crud.CrudSqlProvider;
import com.muyuan.common.repo.jdbc.multi.DataSource;
import com.muyuan.common.repo.jdbc.mybatis.JdbcBaseMapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@DataSource(ProductJdbcConfig.DATASOURCE_NAME)
public interface ProductBaseMapper<T> extends JdbcBaseMapper<T> {

    @SelectProvider(value = CrudSqlProvider.class,method = "selectOne")
    T selectOne(Map params);

    @SelectProvider(value = CrudSqlProvider.class,method = "selectList")
    List<T> selectList(Map params);

    @IdGenerator()
    @SelectProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insert(T dataObject);
}
