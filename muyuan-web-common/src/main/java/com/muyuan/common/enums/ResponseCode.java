package com.muyuan.common.enums;

public enum ResponseCode {
    SUCCESS(0,"操作成功"),
    FAIL(400,"操作失败"),
    AUTH_FAIL(401,"没有权限"),
    ARGUMENT_EEORR(402,"参数错误"),
    UNAUTHORIZED(405,"没有登录或者token失效"),
    CLIENT_AUTHENTICATION_FAILED(406,"OAUTH2 CLIENT认证错误"),
    USER_ONT_FOUND(406,"用户信息没有查询到"),
    FILE_UPLOAD_FAIL(411,"文件上传失败"),
    REPEATABLE_REQUEST_FAIL(413,"请求在处理中，请勿重复提交"),
    TOKEN_NOT_FOUND_FAIL(412,"TOKEN参数未传递"),
    TOKEN_INVALID_FAIL(414,"TOKEN无效"),
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
