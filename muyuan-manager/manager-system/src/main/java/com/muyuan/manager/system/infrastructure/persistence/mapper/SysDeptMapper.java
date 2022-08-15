package com.muyuan.manager.system.infrastructure.persistence.mapper;

import com.muyuan.manager.system.domains.model.SysDept;
import com.muyuan.manager.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptMapper extends SystemBaseMapper<SysDept> {
}
