package com.muyuan.common.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.dto.FileDTO;
import com.muyuan.common.factories.FileServiceFactory;
import com.muyuan.common.service.FileService;
import com.muyuan.common.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/file")
@Api(tags = {"文件接口"})
public class FileController {

    @PostMapping("/upload")
    @ApiOperation(value = "上传单个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",required = true)
    })
    public Result<FileVO> uploadFile(FileDTO fileDTO) {
        long size = fileDTO.getFile().getSize();
        if (GlobalConst.MB < size){
            return ResultUtil.fail("文件大小：{} MB 大于 1MB 无法上传",size >> 20);
        }


        FileService fileService = FileServiceFactory.createFastDFSFileService();

        Optional<FileVO> fileVO = fileService.uploadFile(fileDTO);

        return ResultUtil.success(fileVO.get());
    }
}
