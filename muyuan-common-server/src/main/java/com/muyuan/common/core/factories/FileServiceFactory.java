package com.muyuan.common.core.factories;

import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.dao.FileMapper;
import com.muyuan.common.core.util.FastDFSClient;
import com.muyuan.common.core.service.FileService;
import com.muyuan.common.core.service.impl.FastDFSFileService;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory {

    public static FileService createFastDFSFileService() {
        return new FastDFSFileService(null, ApplicationContextHandler.getContext().getBean(FileMapper.class),ApplicationContextHandler.getContext().getBean(FastDFSClient.class));
    }


}
