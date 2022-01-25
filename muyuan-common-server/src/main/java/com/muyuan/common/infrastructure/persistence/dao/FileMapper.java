package com.muyuan.common.infrastructure.persistence.dao;

import com.muyuan.common.domains.model.File;
import com.muyuan.common.infrastructure.config.mybatis.CommonBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends CommonBaseMapper<File> {
}
