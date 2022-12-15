package com.muyuan.user.domain.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.thread.CommonThreadPool;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.repo.PermissionRepo;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.domain.service.PermissionService;
import com.muyuan.user.face.dto.PermissionCommand;
import com.muyuan.user.face.dto.PermissionQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName PermissionDomainServiceImpl
 * Description 权限
 * @Author 2456910384
 * @Date 2022/10/12 14:55
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepo permissionRepo;

    private RoleRepo roleRepo;

    private RedisCacheService cacheService;

    @Override
    public List<Permission> getPermissionByRoles(Role... roles) {
        List<Permission> permissions = new ArrayList<>();
        for (Role role : roles) {

            List<Permission> roleMenus = CacheServiceUtil.getAndUpdateList(cacheService,
                    getRolePermsKeyPrefix(role.getPlatformType()) + role.getCode(),
                    () -> permissionRepo.selectByRoleCode(role.getCode()), Permission.class);

            permissions.addAll(roleMenus);
        }

        return permissions;
    }

    @Override
    public List<Permission> getPermissionByRoleID(RoleID roleID) {
        Role role = roleRepo.select(roleID.getValue());
        return getPermissionByRoles(role);
    }

    @Override
    public Page<Permission> list(PermissionQueryCommand commend) {
        return permissionRepo.select(commend);
    }

    @Override
    public String checkUnique(Permission.Identify identify) {
        Long id = null == identify.getId() ? 0 : identify.getId();
        Permission permission = permissionRepo.selectPermission(identify);
        if (null != permission && !id.equals(permission.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean addPermission(PermissionCommand command) {
        Permission permission = new Permission();

        permission.setBusiness(command.getBusiness());
        permission.setModule(command.getModule());
        permission.setResource(command.getResource());
        permission.setType(command.getType());
        permission.setPerms(command.getPerms());
        permission.setStatus(command.getStatus());
        permission.setPlatformType(command.getPlatformType());
        permission.setResourceRef(command.getResourceRef());

        return permissionRepo.addPermission(permission);
    }

    @Override
    public Optional<Permission> getPermission(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return permissionRepo.selectPermission(id);
                });
    }

    @Override
    public boolean updatePermission(PermissionCommand command) {
        Permission permission = new Permission();

        permission.setId(command.getId());
        permission.setBusiness(command.getBusiness());
        permission.setModule(command.getModule());
        permission.setResource(command.getResource());
        permission.setType(command.getType());
        permission.setPerms(command.getPerms());
        permission.setStatus(command.getStatus());
        permission.setPlatformType(command.getPlatformType());

        Permission old = permissionRepo.updatePermission(permission);
        if (ObjectUtils.isNotEmpty(old)) {
            List<Role> roles = roleRepo.selectByPermID(old.getId());
            for (Role role : roles) {
                cacheService.delayDoubleDel(getRolePermsKeyPrefix(old.getPlatformType()) + role.getCode());
                cacheService.delayDoubleDel(getRolePermsKeyPrefix(permission.getPlatformType()) + role.getCode());
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePermissionById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList<>(Arrays.asList(ids));

        List<Permission> olds = permissionRepo.deleteBy(removeIds.toArray(new Long[0]));
        permissionRepo.deleteRef(olds.stream().map(Permission::getId).toArray(Long[]::new));

        Runnable task = () -> {
            for (Permission old : olds) {
                List<Role> roles = roleRepo.selectByPermID(old.getId());
                if (ObjectUtils.isNotEmpty(roles)) {
                    for (Role role : roles) {
                        cacheService.delayDoubleDel(getRolePermsKeyPrefix(old.getPlatformType()) + role.getCode());
                    }
                }
            }
        };

        CommonThreadPool.exec(task);

        return !olds.isEmpty();
    }

    private String getRolePermsKeyPrefix(PlatformType platformType) {
        switch (platformType) {
            case OPERATOR:
                return RedisConst.OPERATOR_ROLE_PERM_KEY_PREFIX;
            case MEMBER:
                return RedisConst.MEMBER_ROLE_PERM_KEY_PREFIX;
            case MERCHANT:
                return RedisConst.MERCHANT_ROLE_PERM_KEY_PREFIX;
        }
        return "";
    }
}
