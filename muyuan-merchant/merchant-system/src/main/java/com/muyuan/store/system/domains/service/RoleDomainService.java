package com.muyuan.store.system.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.store.system.domains.dto.RoleDTO;
import com.muyuan.store.system.domains.dto.UserDTO;
import com.muyuan.store.system.domains.model.Role;
import com.muyuan.store.system.domains.model.User;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName RoleQueryImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2021/12/24 11:04
 * @Version 1.0
 */
public interface RoleDomainService {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
     List<Role> getRoleByUserId(Long userId);

    /**
     * 列表查询
     * @param sysRoleDTO
     * @return
     */
    Page<Role> page(RoleDTO sysRoleDTO);

    /**
     * 列表查询
     * @param sysRoleDTO
     * @return
     */
    List<Role> list(RoleDTO sysRoleDTO);

    /**
     * 检验唯一性
     * @param sysRole
     * @return
     */
    boolean checkRoleCodeUnique(Role sysRole);

    /**
     * 列表查询
     * @param userDTO
     * @return
     */
    Page<User> selectAllocatedList(UserDTO userDTO);


    /**
     * 添加角色
     * @param sysRoleDTO
     */
    void add(RoleDTO sysRoleDTO);

    /**
     * 添加角色
     * @param sysRoleDTO
     */
    void update(RoleDTO sysRoleDTO);

    /**
     * 通过ID查询角色信息
     * @param id
     * @return
     */
    Optional<Role> getById(Long id);

    /**
     * 获取角色详情
     * @param role
     * @return
     */
   Optional<Role> get(Role role);


    void deleteById(String... id);

}
