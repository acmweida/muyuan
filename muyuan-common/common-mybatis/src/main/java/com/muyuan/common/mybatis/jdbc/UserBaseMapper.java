package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.muyuan.common.mybatis.config.UserJdbcConfig;
import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataSource(UserJdbcConfig.DATASOURCE_NAME)
public interface UserBaseMapper<T> extends JdbcBaseMapper<T> {

    default int batchInsert(List<T> list) {
        return ((UserBaseMapper)AopContext.currentProxy()).batchInsert(list,100);
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
