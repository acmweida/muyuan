package com.muyuan.system.domain.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.interfaces.dto.SysRoleDTO;

import java.util.List;

/**
 * @ClassName SysRoleDomainService
 * Description 用户角色域服务
 * @Author 2456910384
 * @Date 2022/4/18 15:38
 * @Version 1.0
 */
public interface SysRoleDomainService {

    /**
     * 通过用户id获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(Long userId);

    /**
     * 列表查询
     * @param sysRoleDTO
     * @return
     */
    Page list(SysRoleDTO sysRoleDTO);
}
