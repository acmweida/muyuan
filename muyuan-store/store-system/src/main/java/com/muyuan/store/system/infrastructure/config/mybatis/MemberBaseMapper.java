package com.muyuan.store.system.infrastructure.config.mybatis;

import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.jdbc.JdbcBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.common.mybatis.jdbc.crud.SqlHelper;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DataSource(MemberJdbcConfig.DATASOURCE_NAME)
public interface MemberBaseMapper<T> extends JdbcBaseMapper<T> {

    @SelectProvider(value = CrudSqlProvider.class, method = "selectOne")
    T selectOne(Map params);

    @SelectProvider(value = CrudSqlProvider.class, method = "selectList")
    List<T> selectList(Map params);

    /**
     * 如果需要数据库自动生成ID 请子接口中定义如下方法
     * <p>
     * #       @Options(useGeneratedKeys = true,keyProperty = "id")
     * #       @InsertProvider(value = CrudSqlProvider.class,method = "insert")
     * #       Integer insertAuto(T dataObject);
     *
     * @param dataObject
     * @return
     */
    @IdGenerator()
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insert(T dataObject);

    /**
     *  数据库自增主键（MySQL） 在子Mapper中定义
     @Options(useGeneratedKeys = true, keyProperty = "id")
     @InsertProvider(value = CrudSqlProvider.class, method = "insert")
     Integer insertAuto(org.apache.poi.ss.formula.functions.T dataObject);
     */

    /**
     * 根据指定字段更新指
     *
     * @param entity
     * @param byColumn
     * @return
     */
    @UpdateProvider(value = CrudSqlProvider.class, method = "updateBy")
    Integer updateBy(T entity, String... byColumn);

    /**
     * 根据指定字段更新指定字段
     *
     * @param entity
     * @param column
     * @param byColumn
     * @return
     */
    @UpdateProvider(value = CrudSqlProvider.class, method = "updateColumnBy")
    Integer updateColumnBy(T entity, String[] column, String... byColumn);

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
     *
     * @param param
     * @return
     */
    @DeleteProvider(value = CrudSqlProvider.class, method = "deleteBy")
    Integer deleteBy(Map param);


    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list) {
        return batchInsert(list, DEFAULT_BATCH_SIZE);
    }

    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list, int batchSize) {
        if (list.isEmpty()) {
            return 0;
        }
        String mapperInterFaceName = StatementUtil.getMapperName(this);
        SqlHelper.executeBatch(list, batchSize, ((sqlSession, entity) -> {
            sqlSession.insert(mapperInterFaceName + ".insert", entity);
        }));

        return 0;
    }

    default int batchUpdate() {
        return 0;
    }
}
