package com.muyuan.common.web.exception;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.core.util.ResultUtil;
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
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        Map<String,String> errorInfo = new HashMap();

        for (ObjectError error : allErrors) {
            errorInfo.put(((FieldError)error).getField(),((FieldError)error).getDefaultMessage());
        }
        log.error("hibernate-validator error,{}", errorInfo);
        return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR.getCode(),errorInfo.get(((FieldError)allErrors.get(0)).getField()));
    }

    @ExceptionHandler(MuyuanException.class)
    public Result muyuanExceptionHaneler(MuyuanException e) {
        e.printStackTrace();
        if (e.getClass().isAssignableFrom(MuyuanException.class) ) {
            return ResultUtil.fail(e.getCode(),e.getMessage());
        }
        return unknowRuntimeException(e);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public Result unknowRuntimeException(IllegalArgumentException e) {
        e.printStackTrace();
        log.error("argument error : {}",e.toString());
        return ResultUtil.error();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result unknowRuntimeException(RuntimeException e) {
        e.printStackTrace();
        log.error("RuntimeException error : {}",e.toString());
        return ResultUtil.error();
    }

}
