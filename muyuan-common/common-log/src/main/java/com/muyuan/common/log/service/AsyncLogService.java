package com.muyuan.common.log.service;

import com.muyuan.system.interfaces.dto.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.security.util.SecurityConstants;

/**
 * 异步调用日志服务
 * 
 * @author ruoyi
 */
@Service
public class AsyncLogService
{
//    @Autowired
//    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
//        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
