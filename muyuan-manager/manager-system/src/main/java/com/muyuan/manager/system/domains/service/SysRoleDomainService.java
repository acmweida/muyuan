package com.muyuan.manager.system.domains.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.domains.dto.SysRoleDTO;

import java.util.List;
import java.util.Optional;

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
    Page<SysRole> list(SysRoleDTO sysRoleDTO);

    /**
     * 选择用户
     * @param roleId
     * @param userIds
     */
    void selectUser(Long roleId,Long[] userIds);

    /**
     * 检验唯一性
     * @param sysRole
     * @return
     */
    String checkRoleCodeUnique(SysRole sysRole);


    /**
     * 添加角色
     * @param sysRoleDTO
     */
    void add(SysRoleDTO sysRoleDTO);

    /**
     * 添加角色
     * @param sysRoleDTO
     */
    void update(SysRoleDTO sysRoleDTO);

    /**
     * 通过ID查询角色信息
     * @param id
     * @return
     */
    Optional<SysRole> getById(String id);


    void deleteById(String... id);
}
