package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.id.IdGenerator;
import com.muyuan.common.mybatis.util.StatementUtil;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JdbcBaseMapper<T> extends BaseMapper<T> {

    String ID = "id";

    String NAME = "name";

    String STATUS = "status";

    String TYPE = "type";

    String CREATE_TIME = "createTime";

    String UPDATE_TIME = "updateTime";

    String ORDER_NUM = "orderNum";

    String PARENT_ID = "parentId";


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

    /**
     * 分用方法
     * @param wrapper
     * @param pageSize
     * @param pageNum
     * @return
     */
    default Page<T> page(LambdaQueryWrapper<T> wrapper, int pageSize, int pageNum) {
        Page<T> page = Page.<T>builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();

        IPage<T> page1 = selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                pageNum, pageSize
        ), wrapper);
        List<T> list = page1.getRecords();
        page.setTotal((int) page1.getTotal());
        page.setRows(list);
        return page;
    }


}
