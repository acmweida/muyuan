package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JdbcBaseMapper<T>  extends BaseMapper<T> {

    String ID = "id";

    String NAME = "name";

    String STATUS = "status";

    String TYPE = "type";

    String CREATE_TIME = "createTime";

    String UPDATE_TIME = "updateTime";

    String ORDER_NUM = "orderNum";

    String PARENT_ID = "parentId";

    String CATEGORY_CODE = "categoryCode";

    String CODE = "code";

    String PLATFORM_TYPE = "platformType";

    String ROLE_ID = "roleId";

    String USER_ID = "userId";

    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list) {
        if (list.isEmpty()) {
            return 0;
        }
        String mapperInterFaceName = StatementUtil.getMapperName(this);
        SqlHelper.executeBatch(list.get(0).getClass(), LogFactory.getLog(this.getClass()), list, 100, ((sqlSession, entity) -> {
            sqlSession.insert(mapperInterFaceName + ".insert", entity);
        }));

        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @IdGenerator
    default int batchInsert(List<T> list, int batchSize) {
        if (list.isEmpty()) {
            return 0;
        }
        String mapperInterFaceName = StatementUtil.getMapperName(this);
        SqlHelper.executeBatch(list.get(0).getClass(), LogFactory.getLog(this.getClass()), list, batchSize, ((sqlSession, entity) -> {
            sqlSession.insert(mapperInterFaceName + ".insert", entity);
        }));

        return 0;
    }


}
