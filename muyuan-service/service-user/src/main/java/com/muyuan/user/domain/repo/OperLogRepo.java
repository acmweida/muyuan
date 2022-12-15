package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.face.dto.OperLogQueryCommand;

import java.util.List;

/**
 * 操作日志记录对象 t_oper_log
 *
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 */

public interface OperLogRepo {

    Page<OperLog> select(OperLogQueryCommand command);

    OperLog selectOperLog(Long id);

    OperLog selectOperLog(OperLog.Identify identify);

    boolean addOperLog(OperLog operLog);

    /**
     * 更新信息
     * @param operLog
     * @return old value
     */
    OperLog updateOperLog(OperLog operLog);

    /**
     * 删除
     * @param ids
     * @return old value
     */
    List<OperLog> deleteBy(Long... ids);

}
