package com.muyuan.common.dubbo.exception;

import com.muyuan.common.core.exception.MuyuanException;

import java.io.Serial;

/**
 * @ClassName RpcException
 * Description TODO
 * @Author 2456910384
 * @Date 2022/7/21 13:57
 * @Version 1.0
 */
public class DubboRpcException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3880369791600184613L;

    private final MuyuanException cause;

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
