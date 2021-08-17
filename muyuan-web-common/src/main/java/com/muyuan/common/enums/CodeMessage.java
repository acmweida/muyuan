package com.muyuan.common.enums;

public enum CodeMessage {
    AUTH_FAIL(401,"登录失败");

    CodeMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
