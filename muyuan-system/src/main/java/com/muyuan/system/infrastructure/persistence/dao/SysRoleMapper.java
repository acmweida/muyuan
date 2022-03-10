package com.muyuan.system.infrastructure.persistence.dao;

import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.infrastructure.config.mybatis.SystemBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName RoleMapper
 * Description RoleMapper
 * @Author 2456910384
 * @Date 2021/12/24 11:21
 * @Version 1.0
 */
@Mapper
public interface SysRoleMapper extends SystemBaseMapper<SysRole> {

    List<SysRole> selectRoleByUserId(Long userId);
}
