package com.muyuan.common.service;

import com.muyuan.common.vo.FileVO;
import com.muyuan.common.dto.FileDTO;

import java.util.Optional;

/**
 *  文件操作接口
 */
public interface FileService {

    /**
     * 单文件上传
     * @param fileDTO
     * @return
     */
    Optional<FileVO> uploadFile(FileDTO fileDTO) ;
}
