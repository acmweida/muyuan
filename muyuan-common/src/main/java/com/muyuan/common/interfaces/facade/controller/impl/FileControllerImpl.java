package com.muyuan.common.interfaces.facade.controller.impl;

import com.muyuan.common.constant.CommonConst;
import com.muyuan.common.domains.factories.FileServiceFactory;
import com.muyuan.common.domains.service.FileService;
import com.muyuan.common.domains.vo.FileVO;
import com.muyuan.common.interfaces.facade.controller.FileController;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class FileControllerImpl implements FileController {

    @Override
    public Result<FileVO> uploadFile(MultipartFile file) {
        long size = file.getSize();
        if (CommonConst.MB < size){
            return ResultUtil.renderFail("文件大小：{} 大于 1MB 无法上传",size);
        }


        FileService fileService = FileServiceFactory.createFastDFSFileService();

        Optional<FileVO> fileVO = fileService.uploadFile(file);

        return ResultUtil.render(fileVO);
    }
}
