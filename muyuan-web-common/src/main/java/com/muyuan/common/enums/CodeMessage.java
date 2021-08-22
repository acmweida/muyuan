package com.muyuan.common.enums;

public enum CodeMessage {
    AUTH_FAIL(401,"登录失败"),
    SUCCESS(0,"操作成功"),

    ARGUMENT_EEORR(300,"参数错误"),
    EERROR(500,"未知异常，请联系管理员！");

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
