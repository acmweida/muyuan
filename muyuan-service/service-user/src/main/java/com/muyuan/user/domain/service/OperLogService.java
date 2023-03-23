package com.muyuan.user.domain.service;

import com.muyuan.common.bean.Page;
import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.face.dto.OperLogCommand;
import com.muyuan.user.face.dto.OperLogQueryCommand;

import java.util.Optional;

/**
 * @ClassName OperLogDomainService 接口
 * Description 操作日志记录接口
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
public interface OperLogService {

    /**
     * 操作日志记录分页查询
     * @param command
     * @return
     */
    Page<OperLog> list(OperLogQueryCommand command);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    String checkUnique(OperLog.Identify identify);

    /**
     * 新增操作日志记录信息
     * @param command
     * @return
     */
    void addOperLog(OperLogCommand command);

    /**
     * 查询操作日志记录信息
     * @param id
     * @return
     */
    Optional<OperLog> getOperLog(Long id);

    /**
     * 删除操作日志记录信息
     * @param ids
     * @return
     */
    boolean deleteOperLogById(Long... ids);
}
