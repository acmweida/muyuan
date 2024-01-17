package com.muyuan.common.web.exception;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.dubbo.exception.DubboRpcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<String> errorInfo = new ArrayList<>();

        for (ObjectError error : allErrors) {
            errorInfo.add(error.getDefaultMessage());
        }
        log.error("hibernate-validator error,{}", errorInfo);
        return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR.getCode(), errorInfo.get(0));
    }

    @ExceptionHandler(MuyuanException.class)
    public Result muyuanExceptionHaneler(MuyuanException e) {
        e.printStackTrace();
        return ResultUtil.fail(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public Result unknowRuntimeException(IllegalArgumentException e) {
        e.printStackTrace();
        log.error("argument error : {}", e.toString());
        return ResultUtil.error();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result unknowRuntimeException(RuntimeException e) {
        e.printStackTrace();
        log.error("RuntimeException error : {}", e.toString());
        return ResultUtil.error();
    }

    @ExceptionHandler(DubboRpcException.class)
    public Result muyuanExceptionHaneler(DubboRpcException e) {
        log.error("Rpc Error ",e);
        return ResultUtil.fail(e.getCause().getCode(),e.getCause().getMessage());
    }

}
