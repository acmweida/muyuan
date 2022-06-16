package com.muyuan.common.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.dao.FileMapper;
import com.muyuan.common.service.FileService;
import com.muyuan.common.service.impl.FastDFSFileService;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory {

    public static FileService createFastDFSFileService() {
        return new FastDFSFileService(null, ApplicationContextHandler.getContext().getBean(FileMapper.class));
    }


}
