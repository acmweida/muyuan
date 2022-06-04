package com.muyuan.common.core.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.factories.FileServiceFactory;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.service.FileService;
import com.muyuan.common.core.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Controller
@RequestMapping("/file")
@Api(tags = {"文件接口"})
public class FileController {

    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传单个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",required = true)
    })
    public Result<FileVO> uploadFile(@RequestParam("file") @NotEmpty MultipartFile file) {
        long size = file.getSize();
        if (GlobalConst.MB < size){
            return ResultUtil.fail("文件大小：{} 大于 1MB 无法上传",size);
        }


        FileService fileService = FileServiceFactory.createFastDFSFileService();

        Optional<FileVO> fileVO = fileService.uploadFile(file);

        return ResultUtil.success(fileVO.get());
    }
}
