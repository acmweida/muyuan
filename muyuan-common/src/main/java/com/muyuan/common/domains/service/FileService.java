package com.muyuan.common.domains.service;

import com.muyuan.common.domains.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 *  文件操作接口
 */
public interface FileService {

    /**
     * 单文件上传
     * @param file
     * @return
     */
    Optional<FileVO> uploadFile(MultipartFile file) ;
}
