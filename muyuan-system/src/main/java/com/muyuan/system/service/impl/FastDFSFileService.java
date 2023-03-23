package com.muyuan.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.core.exception.FileUploadFailException;
import com.muyuan.system.base.util.FastDFSClient;
import com.muyuan.system.dao.FileMapper;
import com.muyuan.system.dto.FileDTO;
import com.muyuan.system.dto.vo.FileVO;
import com.muyuan.system.entity.File;
import com.muyuan.system.service.FileService;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
@Service
@AllArgsConstructor
public class FastDFSFileService implements FileService {

    private FileMapper fileMapper;

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

        fileInfo.setUrl(DigestUtils.md5Hex(filePath));


        fileMapper.insert(fileInfo);
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
        File file = fileMapper.selectOne(new LambdaQueryWrapper<File>()
                .eq(File::getUrl, url));
        if (file != null) {
            Optional<FileInfo> fileInfo = FastDFSClient.getFileInfo(file.getFilePath());
            if (fileInfo.isPresent()) {
                return Optional.of(file);
            }
        }

        return  Optional.empty();
    }


}
