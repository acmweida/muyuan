package com.muyuan.user.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.OperLogDTO;
import com.muyuan.user.api.dto.OperLogQueryRequest;
import com.muyuan.user.api.dto.OperLogRequest;

/**
 * 操作日志记录Service接口
 *
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 */
public interface OperLogInterface {

    /**
      * 查询操作日志记录列表
      * @param request
      * @return
      */
    Result<Page<OperLogDTO>> list(OperLogQueryRequest request);

    /**
     * 添加操作日志记录
     * @param request
     * @return
     */
    Result add(OperLogRequest request);

    /**
     * 查询操作日志记录
     * @param id
     * @return
     */
    Result<OperLogDTO> getOperLog(Long id);

    /**
     * 更新操作日志记录
     * @param request
     * @return
     */
    Result updateOperLog(OperLogRequest request);

    /**
     *  删除操作日志记录
     * @param ids
     * @return
     */
    Result deleteOperLog(Long... ids);

}
