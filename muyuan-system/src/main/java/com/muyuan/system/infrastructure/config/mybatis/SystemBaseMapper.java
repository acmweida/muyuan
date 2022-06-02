package com.muyuan.system.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.common.mybatis.jdbc.crud.SqlHelper;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import com.muyuan.common.mybatis.jdbc.mybatis.JdbcBaseMapper;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DataSource(SystemJdbcConfig.DATASOURCE_NAME)
public interface SystemBaseMapper<T> extends JdbcBaseMapper<T> {

    @SelectProvider(value = CrudSqlProvider.class,method = "selectOne")
    T selectOne(Map params);

    @SelectProvider(value = CrudSqlProvider.class,method = "selectList")
    List<T> selectList(Map params);

    @IdGenerator()
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @InsertProvider(value = CrudSqlProvider.class,method = "insert")
    Integer insert(T dataObject);

    /**
     * 更加指定字段更新
     * @param entity
     * @param column
     * @return
     */
    @UpdateProvider(value = CrudSqlProvider.class,method = "updateBy")
    Integer updateBy(T entity,String... column);

    /**
     * TODO:根据条件更新指定字段
     * TODO: 复杂条件更新指定字段
     * @param entity
     * @param column
     * @return
     */
//    @UpdateProvider(value = CrudSqlProvider.class,method = "updateBy")
//    Integer updateBy(T entity,String... column);

    /**
     * 删除
     * @param param
     * @return
     */
    @DeleteProvider(value = CrudSqlProvider.class,method = "deleteBy")
    Integer deleteBy(Map param);


    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list) {
        return batchInsert(list,DEFAULT_BATCH_SIZE);
    }

    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list,int batchSize)  {
        if (list.isEmpty()) {
            return 0;
        }
        String mapperInterFaceName = StatementUtil.getMapperName(this);
        SqlHelper.executeBatch(list,batchSize,((sqlSession, entity) -> {
            sqlSession.insert( mapperInterFaceName+".insert",entity);
        }));

        return 0;
    }

    default int batchUpdate() {
        return 0;
    }
}
