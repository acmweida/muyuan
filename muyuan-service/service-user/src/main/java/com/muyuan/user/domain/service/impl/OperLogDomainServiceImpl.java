package com.muyuan.user.domain.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.domain.repo.OperLogRepo;
import com.muyuan.user.domain.service.OperLogService;
import com.muyuan.user.face.dto.OperLogCommand;
import com.muyuan.user.face.dto.OperLogQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ${author}
 * @ClassName OperLogDomainServiceImpl
 * Description 权限
 * @date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class OperLogDomainServiceImpl implements OperLogService {

    private OperLogRepo operLogRepo;

    @Override
    public Page<OperLog> list(OperLogQueryCommand commend) {
        return operLogRepo.select(commend);
    }

    @Override
    public String checkUnique(OperLog.Identify identify) {
        Long id = null == identify.getId() ? null : identify.getId();
        OperLog operLog = operLogRepo.selectOperLog(identify);
        if (null != operLog && !id.equals(operLog.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Async
    public void addOperLog(OperLogCommand command) {
        OperLog operLog = new OperLog();

        operLog.setTitle(command.getTitle());
        operLog.setBusinessType(command.getBusinessType());
        operLog.setMethod(command.getMethod());
        operLog.setRequestMethod(command.getRequestMethod());
        operLog.setOperatorType(command.getOperatorType());
        operLog.setOperName(command.getOperName());
        operLog.setDeptName(command.getDeptName());
        operLog.setOperUrl(command.getOperUrl());
        operLog.setOperIp(command.getOperIp());
        operLog.setOperLocation(command.getOperLocation());
        operLog.setOperParam(command.getOperParam());
        operLog.setJsonResult(command.getJsonResult());
        operLog.setStatus(command.getStatus());
        operLog.setErrorMsg(command.getErrorMsg());
        operLog.setOperTime(DateTime.now().toDate());

        operLogRepo.addOperLog(operLog);
    }

    @Override
    public Optional<OperLog> getOperLog(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return operLogRepo.selectOperLog(id_);
                });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOperLogById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList(Arrays.asList(ids));

        List<OperLog> olds = operLogRepo.deleteBy(removeIds.toArray(new Long[0]));

        return !olds.isEmpty();
    }


}
