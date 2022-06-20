package com.muyuan.common.service.impl;

import com.muyuan.common.core.exception.handler.FileUploadFailException;
import com.muyuan.common.dao.FileMapper;
import com.muyuan.common.dto.FileDTO;
import com.muyuan.common.model.File;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.service.FileService;
import com.muyuan.common.util.FastDFSClient;
import com.muyuan.common.vo.FileVO;
import com.muyuan.common.web.util.SecurityUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    private FileMapper fileMapper;

    public FastDFSFileService(File file, FileMapper fileMapper) {
        this.file = file;
        this.fileMapper = fileMapper;
    }

    /**
     * 文件上传实现
     *
     * @param fileDTO
     * @return
     */
    public Optional<FileVO> uploadFile(FileDTO fileDTO) {

        MultipartFile file = fileDTO.getFile();
        final String filename = file.getOriginalFilename();
        long size = file.getSize();

        String suffix = filename.substring(filename.lastIndexOf("."));
        InputStream context = null;
        String filePath = null;
        try {
            context = file.getInputStream();
            filePath = FastDFSClient.uploadFile(filename, context);
        } catch (IOException | MyException e) {
            e.printStackTrace();
            throw new FileUploadFailException();
        }

        File fileInfo = new File();
        fileInfo.setFilePath(filePath);
        fileInfo.setName(filename);
        fileInfo.setSize(size);
        fileInfo.setSuffix(suffix);
        fileInfo.setModule(fileDTO.getModule());
        fileInfo.setFunction(fileDTO.getFunction());
        fileInfo.setCreateBy(SecurityUtils.getUserId());
        fileInfo.setCreateTime(DateTime.now().toDate());

        fileInfo.setUrl(DigestUtils.md5Hex(filePath));


        fileMapper.insertAuto(fileInfo);
        FileVO fileVO = new FileVO();
        BeanUtils.copyProperties(fileInfo, fileVO);

        return Optional.of(fileVO);
    }

    @Override
    public byte[] view(String filePath) throws MyException, IOException {
        Assert.notNull(filePath, "filePath is null");
        return FastDFSClient.download(filePath);
    }

    @Override
    public void view(String filePath, OutputStream outputStream) throws MyException, IOException {
        Assert.notNull(filePath, "fileUrl is null");
        Assert.notNull(outputStream, "outputStream is null");
        FastDFSClient.download(filePath, outputStream);
    }

    @Override
    public Optional<File> getFileInfo(String url) throws MyException, IOException {
        File file = fileMapper.selectOne(new SqlBuilder(File.class)
                .eq("url", url)
                .build());
        if (file != null) {
            Optional<FileInfo> fileInfo = FastDFSClient.getFileInfo(file.getFilePath());
            if (fileInfo.isPresent()) {
                return Optional.of(file);
            }
        }

        return  Optional.empty();
    }


}
