package com.muyuan.common.enums;

public enum TokenStatus {
    OK(0,"有效"),
    INVALID(1,"无效");

    TokenStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;


}
