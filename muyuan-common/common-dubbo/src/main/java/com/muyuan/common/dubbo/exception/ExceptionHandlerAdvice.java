package com.muyuan.common.dubbo.exception;

import com.muyuan.common.core.exception.MuyuanExceptionHandler;
import com.muyuan.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DubboRpcException.class)
    public Result muyuanExceptionHaneler(DubboRpcException e) {
        e.printStackTrace();
        MuyuanExceptionHandler handler = e.getCause();
        return handler.handle(e.getCause());
    }

}
