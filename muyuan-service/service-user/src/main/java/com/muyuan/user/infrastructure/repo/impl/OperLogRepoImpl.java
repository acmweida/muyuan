package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
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

@Component
@AllArgsConstructor
public class OperLogRepoImpl implements OperLogRepo {

    private OperLogMapper operLogMapper;

    private OperLogConverter converter;

    @Override
    public Page<OperLog> select(OperLogQueryCommand command) {
        LambdaQueryWrapper<OperLogDO> wrapper = new LambdaQueryWrapper<OperLogDO>()
                .eq(OperLogDO::getId,command.getId())
                .eq(OperLogDO::getTitle,command.getTitle())
                .eq(OperLogDO::getBusinessType,command.getBusinessType())
                .eq(OperLogDO::getMethod,command.getMethod())
                .eq(OperLogDO::getRequestMethod,command.getRequestMethod())
                .eq(OperLogDO::getOperatorType,command.getOperatorType())
                .eq(OperLogDO::getOperName,command.getOperName())
                .eq(OperLogDO::getDeptName,command.getDeptName())
                .eq(OperLogDO::getOperUrl,command.getOperUrl())
                .eq(OperLogDO::getOperIp,command.getOperIp())
                .eq(OperLogDO::getOperLocation,command.getOperLocation())
                .eq(OperLogDO::getStatus,command.getStatus());

        Page<OperLog> page = Page.<OperLog>builder()
                .pageSize(command.getPageSize())
                .pageNum(command.getPageNum())
                .build();

        List<OperLogDO> list = command.enablePage() ?
                operLogMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                operLogMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public OperLog selectOperLog(Long id) {
        OperLogDO operLogDO = operLogMapper.selectOne(new LambdaQueryWrapper<OperLogDO>()
                .eq(OperLogDO::getId, id));
        return converter.to(operLogDO);
    }

    @Override
    public OperLog selectOperLog(OperLog.Identify identify) {
        OperLogDO operLogDO = operLogMapper.selectOne(new LambdaQueryWrapper<OperLogDO>().select(OperLogDO::getId)
                .eq(OperLogDO::getId, identify.getId()));

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
        LambdaQueryWrapper<OperLogDO> wrapper = new LambdaQueryWrapper<OperLogDO>()
                .eq(OperLogDO::getId, operLog.getId());

        OperLogDO operLogDO = operLogMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(operLogDO)) {
            operLogMapper.updateById(converter.to(operLog));
        }

        return converter.to(operLogDO);
    }

    @Override
    public List<OperLog> deleteBy(Long... ids) {
        List<OperLogDO> operLogs = operLogMapper.selectList(new LambdaQueryWrapper<OperLogDO>()
                .in(OperLogDO::getId, ids));

        operLogMapper.deleteBatchIds(operLogs);

        return converter.to(operLogs);
    }

}
