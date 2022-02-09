package com.muyuan.common.core.infrastructure.persistence.dao;

import com.muyuan.common.core.domains.model.File;
import com.muyuan.common.core.infrastructure.config.mybatis.CommonBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends CommonBaseMapper<File> {
}
