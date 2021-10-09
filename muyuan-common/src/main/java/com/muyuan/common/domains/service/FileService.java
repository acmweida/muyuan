package com.muyuan.common.domains.service;

import com.muyuan.common.domains.model.File;
import com.muyuan.common.domains.repo.FileRepo;
import com.muyuan.common.domains.vo.FileVO;

public class FileService {

    private File file;

    private FileRepo fileRepo;

    public FileService(File file,FileRepo fileRepo) {
        this.file = file;
        this.fileRepo = fileRepo;
    }

    public FileVO uploadFile() {
        return null;
    }
}
