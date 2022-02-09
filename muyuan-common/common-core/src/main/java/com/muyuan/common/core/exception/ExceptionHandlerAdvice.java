package com.muyuan.common.core.exception;

import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        Map errorInfo = new HashMap();

        for (ObjectError error : allErrors) {
            errorInfo.put(((FieldError)error).getField(),((FieldError)error).getDefaultMessage());
        }
        log.error("hibernate-validator error", e);
        return ResultUtil.renderFail(ResponseCode.ARGUMENT_EEORR,errorInfo);
    }

    @ExceptionHandler(MuyuanException.class)
    public Result muyuanExceptionHaneler(MuyuanException e) {
        if (e instanceof MuyuanExceptionHandler) {
            MuyuanExceptionHandler handler = (MuyuanExceptionHandler) e;
            return handler.handle(e);
        }
        return unknowRuntimeException(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result unknowRuntimeException(IllegalArgumentException e) {
        e.printStackTrace();
        log.error("argument error : {}",e.toString());
        return ResultUtil.renderError();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result unknowRuntimeException(RuntimeException e) {
        e.printStackTrace();
        log.error("RuntimeException error : {}",e.toString());
        return ResultUtil.renderError();
    }

}
