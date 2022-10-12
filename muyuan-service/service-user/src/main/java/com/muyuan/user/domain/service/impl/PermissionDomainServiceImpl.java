package com.muyuan.user.domain.service.impl;

import com.muyuan.user.domain.model.entity.user.Permission;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.repo.PermissionRepo;
import com.muyuan.user.domain.service.PermissionDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
