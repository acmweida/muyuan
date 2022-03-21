package com.muyuan.system.infrastructure.persistence.dao;

import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDataMapper extends SystemBaseMapper<DictData> {
}
