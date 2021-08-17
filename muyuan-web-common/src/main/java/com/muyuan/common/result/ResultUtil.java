package com.muyuan.common.result;


import com.muyuan.common.enums.ResponseCode;

public class ResultUtil {

    public static Result render() {
        return new Result(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getType(),"操作成功");
    }

    public static Result render(Object data) {
        return new Result(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getType(),"操作成功",data);
    }

    public static Result renderFail(String msg) {
        return new Result(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getType(),msg);
    }

    public static Result renderFail(int code,String msg) {
        return new Result(code,ResponseCode.FAIL.getType(),msg);
    }

}
