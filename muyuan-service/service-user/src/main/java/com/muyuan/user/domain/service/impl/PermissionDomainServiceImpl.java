package com.muyuan.user.domain.service.impl;

import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.repo.PermissionRepo;
import com.muyuan.user.domain.service.PermissionDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PermissionDomainServiceImpl
 * Description 权限
 * @Author 2456910384
 * @Date 2022/10/12 14:55
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class PermissionDomainServiceImpl implements PermissionDomainService {

    private PermissionRepo permissionRepo;

    @Override
    public List<Permission> getPermissionByRoles(List<Role> roles) {
        List<Permission> permissions = new ArrayList<>();
        for (Role role : roles) {
            permissions.addAll(
            permissionRepo.selectByRoles(role.getId()));
        }

        return permissions;
    }
}
