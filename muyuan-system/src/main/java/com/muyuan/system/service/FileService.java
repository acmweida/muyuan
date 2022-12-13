package com.muyuan.system.service;

import com.muyuan.system.dto.FileDTO;
import com.muyuan.system.dto.vo.FileVO;
import com.muyuan.system.entity.File;
import org.csource.common.MyException;

import java.io.IOException;
import java.io.OutputStream;
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
    Optional<FileVO> uploadFile(FileDTO fileDTO);

    /**
     * 小文件下载 文件小于2M
     * @param filePaht
     * @return
     * @throws MyException
     * @throws IOException
     */
    byte[] view(String filePaht) throws MyException, IOException;

    /**
     * 小文件下载 文件小于2M
     * @param filePaht
     * @return
     * @throws MyException
     * @throws IOException
     */
    void view(String filePaht, OutputStream outputStream) throws MyException, IOException;


    Optional<File> getFileInfo(String url) throws MyException, IOException;

}
