package com.muyuan.common.dubbo.exception;

import com.muyuan.common.core.exception.MuyuanException;

/**
 * @ClassName RpcException
 * Description TODO
 * @Author 2456910384
 * @Date 2022/7/21 13:57
 * @Version 1.0
 */
public class DubboRpcException extends Exception {

    private MuyuanException cause;

    public DubboRpcException(MuyuanException cause) {
        super(cause);
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return cause.getMessage();
    }

    public int getCode() {
        return cause.getCode();
    }

    @Override
    public MuyuanException getCause() {
        return cause;
    }
}
