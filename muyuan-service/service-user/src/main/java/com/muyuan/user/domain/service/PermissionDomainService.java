package com.muyuan.user.domain.service;

import com.muyuan.common.bean.Page;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.face.dto.PermissionCommand;
import com.muyuan.user.face.dto.PermissionQueryCommand;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName PermissionDomainService 接口
 * Description 权限接口
 * @Author 2456910384
 * @Date 2022/10/11 14:07
 * @Version 1.0
 */
public interface PermissionDomainService {

    /**
     * 获取权限
     * @param roles
     * @return
     */
    List<Permission> getPermissionByRoles(List<Role> roles);

    /**
     * 获取权限
     * @param role
     * @return
     */
    List<Permission> getPermissionByRole(Role role);

    /**
     * 权限分页查询
     * @param commend
     * @return
     */
    Page<Permission> list(PermissionQueryCommand commend);

    /**
     * 唯一性检查
     * @param key
     * @return
     */
    String checkUnique(Permission.Identify key);

    /**
     * 新增权限信息
     * @param command
     * @return
     */
    boolean addPermission(PermissionCommand command);

    /**
     * 查询权限信息
     * @param id
     * @return
     */
    Optional<Permission> getPermission(Long id);

    /**
     * 更新权限信息
     * @param command
     * @return
     */
    boolean updateMenu(PermissionCommand command);

    /**
     * 删除权限信息
     * @param ids
     * @return
     */
    boolean deletePermissionById(Long... ids);
}
