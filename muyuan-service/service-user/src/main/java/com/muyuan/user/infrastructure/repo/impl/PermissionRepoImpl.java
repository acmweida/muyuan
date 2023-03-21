package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muyuan.common.bean.Page;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.repo.PermissionRepo;
import com.muyuan.user.face.dto.PermissionQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.PermissionConverter;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
import com.muyuan.user.infrastructure.repo.mapper.PermissionMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PermissionRepoImpl implements PermissionRepo {

    private PermissionMapper permissionMapper;

    private PermissionConverter converter;

    @Override
    public List<Permission> selectByRoles(RoleID roleId) {
        List<PermissionDO> permissionDOS = permissionMapper.selectByRoleId(roleId.getValue());
        return converter.to(permissionDOS);
    }

    @Override
    public List<Permission> selectByRoleCode(String roleCode) {
        List<PermissionDO> permissionDOS = permissionMapper.selectByRoleCode(roleCode);
        return converter.to(permissionDOS);
    }

    @Override
    public Page<Permission> select(PermissionQueryCommand command) {
        LambdaQueryWrapper<PermissionDO> wrapper = new LambdaQueryWrapper<PermissionDO>()
                .eq(PermissionDO::getBusiness, command.getBusiness())
                .eq(PermissionDO::getModule, command.getModule())
                .eq(PermissionDO::getStatus, command.getStatus())
                .eq(PermissionDO::getType, command.getType())
                .in(PermissionDO::getType, (Object) command.getTypes())
                .like(PermissionDO::getResource, command.getResource())
                .eq(ObjectUtils.isEmpty(command.getPlatformType()), PermissionDO::getPlatformType, command.getPlatformType().getCode())
                .orderByDesc(PermissionDO::getBusiness, PermissionDO::getModule);

        Page<Permission> page = Page.<Permission>builder()
                .pageSize(command.getPageSize())
                .pageNum(command.getPageNum())
                .build();

        List<PermissionDO> list;
        if (command.enablePage()) {
            IPage<PermissionDO> page1 = permissionMapper.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                    command.getPageNum(), command.getPageSize()
            ), wrapper);
            list = page1.getRecords();
            page.setTotal((int) page1.getTotal());
        } else {
            list = permissionMapper.selectList(wrapper);
        }

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Permission selectPermission(Long id) {
        PermissionDO menuDO = permissionMapper.selectOne(new LambdaQueryWrapper<PermissionDO>()
                .eq(PermissionDO::getId, id));
        return converter.to(menuDO);
    }

    @Override
    public Permission selectPermission(Permission.Identify identify) {
        PermissionDO permissionDO = permissionMapper.selectOne(new LambdaQueryWrapper<PermissionDO>().select(PermissionDO::getId)
                .eq(ObjectUtils.isNotEmpty(identify.getId()), PermissionDO::getId, identify.getId())
                .eq(PermissionDO::getPerms, identify.getPerms()));

        return converter.to(permissionDO);
    }

    @Override
    public boolean addPermission(Permission permission) {
        PermissionDO to = converter.to(permission);
        Integer count = permissionMapper.insertAuto(to);
        permission.setId(to.getId());
        return count > 0;
    }

    @Override
    public Permission updatePermission(Permission permission) {
        LambdaQueryWrapper<PermissionDO> wrapper = new LambdaQueryWrapper<PermissionDO>()
                .eq(PermissionDO::getId, permission.getId());

        PermissionDO permissionDO = permissionMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(permissionDO)) {
            permissionMapper.deleteById(converter.to(permission));
        }

        return converter.to(permissionDO);
    }

    @Override
    public List<Permission> deleteBy(Long... ids) {
        LambdaQueryWrapper<PermissionDO> wrapper = new LambdaQueryWrapper<PermissionDO>()
                .in(PermissionDO::getId, ids);
        List<PermissionDO> permissions = permissionMapper.selectList(wrapper);

        permissionMapper.delete(wrapper);

        return converter.to(permissions);
    }

    @Override
    public boolean deleteRef(Long... permIds) {
        if (permIds.length == 0) {
            return false;
        }
        return permissionMapper.deleteRef(permIds) > 0;
    }

}
