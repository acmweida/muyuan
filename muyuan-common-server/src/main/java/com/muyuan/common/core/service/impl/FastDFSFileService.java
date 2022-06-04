package com.muyuan.common.core.service.impl;

import com.muyuan.common.core.dao.FileMapper;
import com.muyuan.common.core.exception.handler.FileUploadFailException;
import com.muyuan.common.core.model.File;
import com.muyuan.common.core.service.FileService;
import com.muyuan.common.core.util.FastDFSClient;
import com.muyuan.common.core.vo.FileVO;
import com.muyuan.common.web.util.SecurityUtils;
import org.csource.common.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @ClassName FastDFSFileService
 * Description FastDFS 文件操作类
 * @Author 2456910384
 * @Date 2021/10/11 11:19
 * @Version 1.0
 */
public class FastDFSFileService implements FileService {

    private File file;

    private FastDFSClient fastDFSClient;

    private FileMapper fileMapper;

    public FastDFSFileService(File file, FileMapper fileMapper, FastDFSClient fastDFSClient) {
        this.file = file;
        this.fileMapper = fileMapper;
        this.fastDFSClient = fastDFSClient;
    }

    /**
     * 文件上传实现
     * @param file
     * @return
     */
    public Optional<FileVO> uploadFile(MultipartFile file)  {

        final String filename = file.getOriginalFilename();
        long size = file.getSize();

        String suffix = filename.substring(filename.lastIndexOf(".")+1);
        InputStream context =null;
        String filePath = null;
        try {
            context = file.getInputStream();
            filePath = fastDFSClient.uploadFile(suffix, context);
        } catch (IOException | MyException e) {
            throw new FileUploadFailException();
        }

        File fileInfo = new File();
        fileInfo.setUrl(filePath);
        fileInfo.setName(filename);
        fileInfo.setSize(size);
        fileInfo.setUpUser(SecurityUtils.getUserId());

        /**
         * TODO : 通过字典功能设置数据
         */
        fileInfo.setVerifyResult((short) 0);
        fileInfo.setVerifyStatus((short) 0);

        fileMapper.insert(fileInfo);
        FileVO fileVO = new FileVO();
        BeanUtils.copyProperties(fileInfo,fileVO);

        return Optional.of(fileVO);
    }
}
