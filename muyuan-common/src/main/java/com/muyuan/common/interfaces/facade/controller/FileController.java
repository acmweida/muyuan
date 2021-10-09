package com.muyuan.common.interfaces.facade.controller;

import com.muyuan.common.domains.vo.FileVO;
import com.muyuan.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public interface FileController {

    Result<FileVO> uploadFile(@RequestParam("file") MultipartFile file);

}
