//package com.muyuan.common.dubbo.exception;
//
//import com.muyuan.common.bean.Result;
//import com.muyuan.common.core.util.ResultUtil;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class DubboExceptionHandlerAdvice {
//
//    @ExceptionHandler(DubboRpcException.class)
//    public Result muyuanExceptionHaneler(DubboRpcException e) {
//        log.error("Rpc Error ",e);
//        return ResultUtil.fail(e.getCause().getCode(),e.getCause().getMessage());
//    }
//
//}
