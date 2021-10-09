package com.muyuan.common.interfaces.facade.controller.impl;

import com.muyuan.common.domains.vo.FileVO;
import com.muyuan.common.interfaces.facade.controller.FileController;
import com.muyuan.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

public class FileControllerImpl implements FileController {
    @Override
    public Result<FileVO> uploadFile(MultipartFile file) {
        return null;
    }
}
