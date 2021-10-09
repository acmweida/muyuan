package com.muyuan.common.domains.repo;

import com.muyuan.common.domains.model.File;

public interface FileRepo {

    File selectOne(long fileId);
}
