package com.muyuan.common.interfaces.facade.controller;

import com.muyuan.common.domains.vo.FileVO;
import com.muyuan.common.result.Result;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Controller
@RequestMapping("/file")
@Api(tags = {"文件接口"})
public interface FileController {

    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传单个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",required = true)
    })
    Result<FileVO> uploadFile(@RequestParam("file") @NotEmpty MultipartFile file);

}
