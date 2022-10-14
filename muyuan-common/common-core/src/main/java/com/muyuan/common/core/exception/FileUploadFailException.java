package com.muyuan.common.core.exception;

import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FileUploadFaileException
 * Description 文件上传失败异常
 * @Author 2456910384
 * @Date 2021/10/12 16:28
 * @Version 1.0
 */
@Slf4j
public class FileUploadFailException extends MuyuanException {

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
