package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.JdbcBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.domain.repo.OperLogRepo;
import com.muyuan.user.face.dto.OperLogQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.OperLogConverter;
import com.muyuan.user.infrastructure.repo.dataobject.OperLogDO;
import com.muyuan.user.infrastructure.repo.mapper.OperLogMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;
import static com.muyuan.user.infrastructure.repo.mapper.OperLogMapper.*;

@Component
@AllArgsConstructor
public class OperLogRepoImpl implements OperLogRepo {

    private OperLogMapper operLogMapper;

    private OperLogConverter converter;

    @Override
    public Page<OperLog> select(OperLogQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(OperLogDO.class)
                .eq(JdbcBaseMapper.ID,command.getId())
                .eq(TITLE,command.getTitle())
                .eq(BUSINESS_TYPE,command.getBusinessType())
                .eq(METHOD,command.getMethod())
                .eq(REQUEST_METHOD,command.getRequestMethod())
                .eq(OPERATOR_TYPE,command.getOperatorType())
                .eq(OPER_NAME,command.getOperName())
                .eq(DEPT_NAME,command.getDeptName())
                .eq(OPER_URL,command.getOperUrl())
                .eq(OPER_IP,command.getOperIp())
                .eq(OPER_LOCATION,command.getOperLocation())
                .eq(OPER_PARAM,command.getOperParam())
                .eq(JSON_RESULT,command.getJsonResult())
                .eq(JdbcBaseMapper.STATUS,command.getStatus())
                .eq(ERROR_MSG,command.getErrorMsg())
                .eq(OPER_TIME,command.getOperTime())
;

        Page<OperLog> page = Page.<OperLog>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<OperLogDO> list = operLogMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public OperLog selectOperLog(Long id) {
        OperLogDO operLogDO = operLogMapper.selectOne(new SqlBuilder(OperLogDO.class)
                .eq(ID, id)
                .build());
        return converter.to(operLogDO);
    }

    @Override
    public OperLog selectOperLog(OperLog.Identify identify) {
        OperLogDO operLogDO = operLogMapper.selectOne(new SqlBuilder(OperLogDO.class).select(ID)
                .eq(ID, identify.getId())
                .build());

        return converter.to(operLogDO);
    }

    @Override
    public boolean addOperLog(OperLog operLog) {
        OperLogDO to = converter.to(operLog);
        Integer count = operLogMapper.insertAuto(to);
        return count > 0;
    }

    @Override
    public OperLog updateOperLog(OperLog operLog) {
        SqlBuilder sqlBuilder = new SqlBuilder(OperLogDO.class)
                .eq(ID, operLog.getId());

        OperLogDO operLogDO = operLogMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(operLogDO)) {
            operLogMapper.updateBy(converter.to(operLog), ID);
        }

        return converter.to(operLogDO);
    }

    @Override
    public List<OperLog> deleteBy(Long... ids) {
        List<OperLogDO> operLogs = operLogMapper.selectList(new SqlBuilder(OperLogDO.class)
                .in(ID, ids)
                .build());

        operLogMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(operLogs);
    }

}
