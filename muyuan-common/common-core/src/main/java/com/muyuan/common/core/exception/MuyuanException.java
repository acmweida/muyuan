package com.muyuan.common.core.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MuyuanException extends RuntimeException {


    private int code;

    private String message;

    public MuyuanException(int code,String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
//        log.error("error code :{} -> message:{}",code,message);
        return message;
    }

}
