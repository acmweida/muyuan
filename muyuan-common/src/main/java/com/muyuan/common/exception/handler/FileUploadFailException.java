package com.muyuan.common.exception.handler;

import com.muyuan.common.enums.ResponseCode;
import com.muyuan.common.exception.MuyuanException;
import com.muyuan.common.exception.MuyuanExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FileUploadFaileException
 * Description 文件上传失败异常
 * @Author 2456910384
 * @Date 2021/10/12 16:28
 * @Version 1.0
 */
@Slf4j
public class FileUploadFailException extends MuyuanException implements MuyuanExceptionHandler {

    public FileUploadFailException() {
        this(ResponseCode.FILE_UPLOAD_FAIL.getCode(), ResponseCode.FILE_UPLOAD_FAIL.getMsg());
    }

    public FileUploadFailException(String message) {
        this(ResponseCode.FILE_UPLOAD_FAIL.getCode(), message);
    }
    public FileUploadFailException(int code, String message) {
        super(code, message);
    }
}
