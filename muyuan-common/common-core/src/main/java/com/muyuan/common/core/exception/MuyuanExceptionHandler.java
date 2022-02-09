package com.muyuan.common.core.exception;

import com.muyuan.common.core.result.Result;

public interface MuyuanExceptionHandler {

    Result handle(MuyuanException e);

}
