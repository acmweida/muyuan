package com.muyuan.common.core.enums;

public enum ResponseCode {
    SUCCESS(0,"操作成功"),

    ARGUMENT_EEORR(100,"参数错误"),


    FAIL(400,"操作失败"),

    AUTH_FAIL(401,"没有权限"),
    TOKEN_INVALID_FAIL(402,"TOKEN无效"),
    TOKEN_NOT_FOUND_FAIL(403,"TOKEN参数未传递"),
    UNAUTHORIZED(405,"没有登录或者token失效"),
    CLIENT_AUTHENTICATION_FAILED(406,"OAUTH2 CLIENT认证错误"),

    AUTHORIZED_ERROR(407,"认证异常"),

    USER_ONT_FOUND(801,"用户信息没有查询到"),
    FILE_UPLOAD_FAIL(601,"文件上传失败"),
    REPEATABLE_REQUEST_FAIL(701,"请求在处理中，请勿重复提交"),

    ERROR(500,"未知异常，请联系管理员！");

    private int code;
    private String msg;

    ResponseCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
