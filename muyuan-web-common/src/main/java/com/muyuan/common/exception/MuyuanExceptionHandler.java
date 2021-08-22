package com.muyuan.common.exception;

import com.muyuan.common.result.Result;

public interface MuyuanExceptionHandler {

    Result handle(MuyuanException e);

}
