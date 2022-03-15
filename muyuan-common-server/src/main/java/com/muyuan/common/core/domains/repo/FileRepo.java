package com.muyuan.common.core.domains.repo;

import com.muyuan.common.core.domains.model.File;

public interface FileRepo {

    File selectOne(long fileId);

    void insert(File file);
}
