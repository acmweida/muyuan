package com.muyuan.system.infrastructure.persistence.mapper;

import com.muyuan.system.domain.model.SysDept;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptMapper extends SystemBaseMapper<SysDept> {
}
