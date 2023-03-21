package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.muyuan.common.mybatis.config.GoodsJdbcConfig;
import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.jdbc.multi.DataSource;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataSource(GoodsJdbcConfig.DATASOURCE_NAME)
public interface GoodsBaseMapper<T> extends JdbcBaseMapper<T> {


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
