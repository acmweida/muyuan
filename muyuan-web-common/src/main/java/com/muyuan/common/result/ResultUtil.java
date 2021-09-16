package com.muyuan.common.result;

import com.muyuan.common.enums.ResponseCode;

import java.util.Collections;

public class ResultUtil {

    public static Result render() {
        return new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getType(),ResponseCode.SUCCESS.getMsg());
    }

    public static Result render(Object data) {
        return new Result(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getType(),"操作成功",data);
    }

    public static Result render(String msg) {
        return new Result(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getType(),msg);
    }

    public static Result render(String msg,Object data) {
        return new Result(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getType(),msg,data);
    }

    public static Result renderFail(String msg) {
        return new Result(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getType(),msg);
    }

    public static Result renderFail(ResponseCode codeMessage) {
        return new Result(codeMessage.getCode(),ResponseCode.FAIL.getType(),codeMessage.getMsg());
    }

    public static Result renderFail(ResponseCode codeMessage,Object data) {
        return new Result(codeMessage.getCode(),ResponseCode.FAIL.getType(),codeMessage.getMsg(),data);
    }

    public static Result renderForbidden() {
        return renderForbidden(ResponseCode.AUTH_FAIL.getMsg());
    }

    public static Result renderForbidden(String msg) {
        return new Result(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getType(),msg);
    }


    public static Result renderFail(int code,String msg) {
        return new Result(code,ResponseCode.FAIL.getType(),msg);
    }

    public static Result renderError() { return renderError(Collections.EMPTY_LIST);};
    public static Result renderError(String code,String message) { return renderError(Collections.EMPTY_LIST);};

    public static Result renderError(Object data) { return new Result(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getType(),ResponseCode.ERROR.getMsg(),data );};

}
