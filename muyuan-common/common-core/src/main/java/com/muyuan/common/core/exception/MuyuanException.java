package com.muyuan.common.core.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;

@Slf4j
public class MuyuanException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1938938809914934754L;

    @Getter
    private int code;

    private final String message;

    public MuyuanException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
