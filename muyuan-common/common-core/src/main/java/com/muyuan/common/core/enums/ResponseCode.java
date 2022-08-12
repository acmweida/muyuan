package com.muyuan.common.core.enums;

public enum ResponseCode {

    SUCCESS(0,"操作成功"),
    FAIL(400,"操作失败"),
    ARGUMENT_ERROR(100,"参数错误"),
    ERROR(500,"未知异常，请联系管理员！"),

    /** 权限 */
    AUTH_FAIL(403,"没有权限"),
    TOKEN_INVALID_FAIL(402,"TOKEN无效"),
    TOKEN_NOT_FOUND_FAIL(403,"TOKEN参数未传递"),
    UNAUTHORIZED(405,"没有登录或者token失效"),
    CLIENT_AUTHENTICATION_FAILED(406,"OAUTH2 CLIENT认证错误"),

    /** 通用 */
    ADD_EXIST(410,"新增内容已存在"),
    QUERY_NOT_EXIST(411,"数据未找到"),

    /**
     * 登录
     */
    CAPTCHA_ERROR(408,"验证码错误"),
    LOGIN_INFO_ERROR(409,"用户名或密码错误"),
    AUTHORIZED_ERROR(407,"认证异常,请联系管理员!"),
    USER_ONT_FOUND(801,"用户信息没有查询到"),

    /** 文件 */
    FILE_UPLOAD_FAIL(601,"文件上传失败"),


    REPEATABLE_REQUEST_FAIL(701,"请求在处理中，请勿重复提交");


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
