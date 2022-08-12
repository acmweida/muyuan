package com.muyuan.common.log.service;

import com.muyuan.menager.interfaces.to.SysOperLogTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 * 
 * @author 
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
    public void saveSysLog(SysOperLogTO sysOperLogTO)
    {
//        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
