package com.muyuan.manager.system.infrastructure.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.SystemBaseMapper;
import com.muyuan.manager.system.domains.model.SysDept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptMapper extends SystemBaseMapper<SysDept> {
}
