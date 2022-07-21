package com.muyuan.common.dubbo.filter;

import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.dubbo.exception.DubboRpcException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @ClassName ExceptionFilter
 * Description 异常包装Filter
 * @Author 2456910384
 * @Date 2022/7/21 14:03
 * @Version 1.0
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class ExceptionFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result appResponse = invoker.invoke(invocation);
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            Throwable exception = appResponse.getException();
            if (exception instanceof MuyuanException ) {
                appResponse.setException(new DubboRpcException((MuyuanException) exception));
            }
        }
        return appResponse;
    }
}
