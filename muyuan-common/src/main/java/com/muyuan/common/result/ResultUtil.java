package com.muyuan.common.result;

import com.muyuan.common.enums.ResponseCode;
import com.muyuan.common.util.InternalStrUtil;

import java.util.Collections;

public class ResultUtil {

    public static Result render() {
        return new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static Result render(Object data) {
        return new Result(ResponseCode.SUCCESS.getCode(),"操作成功",data);
    }

    public static Result render(String msg) {
        return new Result(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static Result render(String msg,Object data) {
        return new Result(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static Result renderFail(String format,Object... args) {
        return new Result(ResponseCode.FAIL.getCode(), InternalStrUtil.stringFormat(format,args));
    }

    public static Result renderFail(String msg) {
        return new Result(ResponseCode.FAIL.getCode(),msg);
    }

    public static Result renderFail(ResponseCode codeMessage) {
        return new Result(codeMessage.getCode(),codeMessage.getMsg());
    }

    public static Result renderFail(ResponseCode codeMessage,Object data) {
        return new Result(codeMessage.getCode(),codeMessage.getMsg(),data);
    }

    public static Result renderForbidden() {
        return renderForbidden(ResponseCode.AUTH_FAIL.getMsg());
    }

    public static Result renderForbidden(String msg) {
        return new Result(ResponseCode.FAIL.getCode(), msg);
    }


    public static Result renderFail(int code,String msg) {
        return new Result(code,msg);
    }

    public static Result renderError() { return renderError(Collections.EMPTY_LIST);};
    public static Result renderError(int code,String message) { return new Result(code,message);};

    public static Result renderError(Object data) { return new Result(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),data );};

    public static boolean isSuccess(Result result) {
        return ResponseCode.SUCCESS.getCode() == result.getCode();
    }


}
