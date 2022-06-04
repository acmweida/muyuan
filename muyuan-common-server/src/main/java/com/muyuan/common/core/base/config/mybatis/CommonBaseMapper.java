package com.muyuan.common.core.base.config.mybatis;

import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import com.muyuan.common.mybatis.jdbc.mybatis.JdbcBaseMapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@DataSource(CommonJdbcConfig.DATASOURCE_NAME)
public interface CommonBaseMapper<T> extends JdbcBaseMapper<T> {

    @SelectProvider(value = CrudSqlProvider.class,method = "selectOne")
    T selectOne(Map params);

    @SelectProvider(value = CrudSqlProvider.class,method = "selectList")
    List<T> selectList(Map params);

    @IdGenerator()
    @SelectProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insert(T dataObject);

    /**
     * 默认根据 id 更新
     * @param entity
     * @return
     */
    @SelectProvider(value = CrudSqlProvider.class,method = "update")
    Integer update(T entity);

    /**
     * 更加指定字段更新
     * @param entity
     * @param column
     * @return
     */
    @SelectProvider(value = CrudSqlProvider.class,method = "updateBy")
    Integer updateBy(T entity,String... column);
}
