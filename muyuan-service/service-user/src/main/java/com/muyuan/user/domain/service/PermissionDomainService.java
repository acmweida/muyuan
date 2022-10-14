package com.muyuan.user.domain.service;

import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.entity.Role;

import java.util.List;

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

}
