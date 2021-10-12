package com.muyuan.common.exception;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import lombok.Data;
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
        log.error("error code :{} -> message:{}",code,message);
        return ResultUtil.renderFail(code,message);
    }
}
