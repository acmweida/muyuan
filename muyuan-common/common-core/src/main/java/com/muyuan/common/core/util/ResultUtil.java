package com.muyuan.common.core.util;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;

import java.util.Collections;
import java.util.function.Supplier;

public class ResultUtil {

    public static Result success() {
        return new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static Result fail() {
        return new Result(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return new Result(ResponseCode.SUCCESS.getCode(), "操作成功", data);
    }

    public static Result success(String msg) {
        return new Result(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static Result fail(String format, Object... args) {
        return new Result(ResponseCode.FAIL.getCode(), StrUtil.stringFormat(format, args));
    }

    public static Result fail(String msg) {
        return new Result(ResponseCode.FAIL.getCode(), msg);
    }

    public static Result fail(ResponseCode codeMessage) {
        return new Result(codeMessage.getCode(), codeMessage.getMsg());
    }

    public static Result fail(ResponseCode codeMessage, String data) {
        return new Result(codeMessage.getCode(), codeMessage.getMsg(), data);
    }

    public static Result forbidden() {
        return forbidden(ResponseCode.AUTH_FAIL.getMsg());
    }

    public static Result forbidden(String msg) {
        return new Result(ResponseCode.FAIL.getCode(), msg);
    }

    public static Result fail(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result error() {
        return error(Collections.EMPTY_LIST);
    }

    public static Result error(ResponseCode code) {
        return error(code.getCode(), code.getMsg());
    }

    public static Result error(int code, String message) {
        return new Result(code, message);
    }

    public static Result error(Object data) {
        return new Result(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), data);
    }

    public static boolean isSuccess(Result result) {
        return ResponseCode.SUCCESS.getCode() == result.getCode();
    }


    public static <T> T getOr(Result<T> result, Supplier<T> supplier) {
        return isSuccess(result) ? result.getData() : supplier == null ? null : supplier.get();
    }

}
