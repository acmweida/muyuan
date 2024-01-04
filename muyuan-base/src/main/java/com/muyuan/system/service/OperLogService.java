package com.muyuan.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.system.dto.OperLogQueryParams;
import com.muyuan.user.api.dto.OperLogDTO;
import com.muyuan.user.api.dto.OperLogRequest;

import java.util.Optional;

/**
 * @ClassName OperLogService
 * Description 操作日志记录服务
 * @Author  ${author}
 * @Date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
public interface OperLogService {

    /**
     * 查询操作日志记录
     * @param params
     * @return
     */
    Page<OperLogDTO> list(OperLogQueryParams params);

    /**
     * 操作日志记录添加
     * @param request
     */
    Result add(OperLogRequest request);

    /**
     * 操作日志记录查询
     * @param id
     * @return
     */
    Optional<OperLogDTO> get(Long id);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);
}
