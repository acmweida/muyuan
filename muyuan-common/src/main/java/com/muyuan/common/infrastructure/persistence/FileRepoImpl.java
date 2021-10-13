package com.muyuan.common.infrastructure.persistence;

import com.muyuan.common.constant.JdbcValueConst;
import com.muyuan.common.domains.model.File;
import com.muyuan.common.domains.repo.FileRepo;
import com.muyuan.common.infrastructure.persistence.dao.FileMapper;
import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileRepoImpl implements FileRepo {

    @Autowired
    FileMapper fileMapper;

    @Override
    public File selectOne(long fileId) {
        return fileMapper.selectOne(new SqlBuilder(File.class)
                .eq("id",fileId)
                .eq("delete", JdbcValueConst.BOOL_FALSE)
                .build());
    }

    @Override
    public void insert(File file) {
         fileMapper.insert(file);
    }
}
