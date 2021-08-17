package com.muyuan.common.enums;

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    FAIL(400,"FAIL"),
    ERROR(500,"ERROR");

    private int code;


    private String type;

    ResponseCode(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }


    public String getType() {
        return type;
    }
}
