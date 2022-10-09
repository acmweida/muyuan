package com.muyuan.common.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.dto.FileDTO;
import com.muyuan.common.factories.FileServiceFactory;
import com.muyuan.common.model.File;
import com.muyuan.common.service.FileService;
import com.muyuan.common.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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


    @GetMapping("/{fileUrl}")
    @ApiOperation(value = "下载单个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileUrl",value = "文件路径",required = true,type ="path")
    })
    public void view(@PathVariable  String fileUrl, HttpServletResponse response) throws MyException, IOException {
        FileService fileService = FileServiceFactory.createFastDFSFileService();
        Optional<File> fileInfo = fileService.getFileInfo(fileUrl);
        if (!fileInfo.isPresent()) {
            response.reset();
            response.getWriter().write(JSONUtil.toJsonString(ResultUtil.fail("文件未找到")));
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            return;
        }

        File file = fileInfo.get();

        response.reset();
        response.setCharacterEncoding(GlobalConst.UTF8);
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(file.getName(),GlobalConst.UTF8));

        if (fileInfo.get().getSize() < (5 << 10 << 10)) {
            IOUtils.write(fileService.view(file.getFilePath()), response.getOutputStream());
            return;
        }

        fileService.view(file.getFilePath(),response.getOutputStream());

    }
}
