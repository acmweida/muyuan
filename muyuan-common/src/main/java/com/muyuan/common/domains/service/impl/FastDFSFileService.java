package com.muyuan.common.domains.service.impl;

import com.muyuan.common.domains.model.File;
import com.muyuan.common.domains.repo.FileRepo;
import com.muyuan.common.domains.service.FileService;
import com.muyuan.common.domains.vo.FileVO;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadCallback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    private StorageClient1 storageClient;

    private FileRepo fileRepo;

    public FastDFSFileService(File file,FileRepo fileRepo,StorageClient1 storageClient) {
        this.file = file;
        this.fileRepo = fileRepo;
        this.storageClient = storageClient;
    }

    public Optional<FileVO> uploadFile(MultipartFile file)  {

        final String filename = file.getOriginalFilename();
        long size = file.getSize();


        String suffix = filename.substring(filename.lastIndexOf(".")+1);
        byte[] context = new byte[0];
        try {
            context = file.getBytes();
            String strings = storageClient.upload_file1(context, filename, new NameValuePair[]{});
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
