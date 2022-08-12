package com.muyuan.common.core.exception;

import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MuyuanException extends RuntimeException implements MuyuanExceptionHandler {


    private int code;

    private String message;

    public MuyuanException(int code,String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public Result handle(MuyuanException e) {
        e.printStackTrace();
        log.error("error code :{} -> message:{}",code,message);
        return ResultUtil.fail(code,message);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
