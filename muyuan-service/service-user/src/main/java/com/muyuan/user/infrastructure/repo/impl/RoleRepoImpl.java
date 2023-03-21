package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.face.dto.RoleQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.UserRoleDO;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import com.muyuan.user.infrastructure.repo.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RoleRepoImpl
 * Description Role
 * @Author 2456910384
 * @Date 2022/10/12 15:13
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class RoleRepoImpl implements RoleRepo {

    private RoleMapper roleMapper;

    private UserConverter converter;

    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> selectRoleByUserId(UserID userId, PlatformType platformType) {
        List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(userId.getValue(), platformType.getCode());
        return converter.toRoles(roleDOS);
    }

    @Override
    public Page<Role> select(RoleQueryCommand command) {
        LambdaQueryWrapper<RoleDO> wrapper = new LambdaQueryWrapper<RoleDO>()
                .eq(RoleDO::getName, command.getName())
                .eq(RoleDO::getStatus, command.getStatus())
                .eq(RoleDO::getPlatformType,command.getPlatformType().getCode())
                .orderByAsc(RoleDO::getOrderNum);

        Page<Role> page = Page.<Role>builder()
                .pageSize(command.getPageSize())
                .pageNum(command.getPageNum())
                .build();

        List<RoleDO> list;
        if (command.enablePage()) {
            IPage<RoleDO> page1 = roleMapper.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                    command.getPageNum(), command.getPageSize()
            ), wrapper);
            list = page1.getRecords();
            page.setTotal((int) page1.getTotal());
        } else {
            list = roleMapper.selectList(wrapper);
        }

        page.setRows(converter.toRoles(list));

        return page;
    }

    @Override
    public Role select(Long id) {
        RoleDO roleDO = roleMapper.selectOne(new LambdaQueryWrapper<RoleDO>()
                .eq(RoleDO::getId, id));
        return converter.toRole(roleDO);
    }

    @Override
    public Role selectRole(Role.Identify identify) {
        RoleDO roleDO = roleMapper.selectOne(new LambdaQueryWrapper<RoleDO>().select(RoleDO::getId)
                .eq(RoleDO::getId, identify.getId().getValue())
                .eq(RoleDO::getPlatformType, identify.getPlatformType().getCode())
                .eq(RoleDO::getCode,identify.getCode()));

        return converter.toRole(roleDO);
    }

    @Override
    public List<Role> selectByMenuID(MenuID menuId) {
        if (ObjectUtils.isEmpty(menuId)) {
            return GlobalConst.EMPTY_LIST;
        }
        return converter.toRoles(roleMapper.selectRoleByMenuID(menuId.getValue()));
    }

    @Override
    public List<Role> selectByPermID(Long permId) {
        if (ObjectUtils.isEmpty(permId)) {
            return GlobalConst.EMPTY_LIST;
        }
        return converter.toRoles(roleMapper.selectRoleByPermID(permId));
    }

    @Override
    public boolean deleteRef(RoleID roleID, Long... permissionIds) {
        return roleMapper.deleteRef(roleID.getValue(), permissionIds) > 0;
    }

    @Override
    public boolean addRef(RoleID roleID, Long... permissionIds) {
        return roleMapper.addRef(roleID.getValue(), permissionIds) > 0;
    }

    @Override
    public boolean addRole(Role role) {
        RoleDO to = converter.to(role);
        Integer count = roleMapper.insert(to);
        role.setId(new RoleID(to.getId()));
        return count > 0;
    }

    @Override
    public Role updateRole(Role role) {
        LambdaQueryWrapper<RoleDO> wrapper = new LambdaQueryWrapper<RoleDO>()
                .eq(RoleDO::getId, role.getId().getValue());

        RoleDO roleDO = roleMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(roleDO)) {
            roleMapper.updateById(converter.to(role));
        }

        return converter.toRole(roleDO);
    }

    @Override
    public List<Role> deleteBy(Long... ids) {
        List<RoleDO> roleDOS = roleMapper.selectList(new LambdaQueryWrapper<RoleDO>()
                .in(RoleDO::getId, ids));

        roleMapper.deleteBatchIds(Lists.newArrayList(Arrays.stream(ids).iterator()));

        return converter.toRoles(roleDOS);
    }

    @Override
    public boolean insertRef(RoleID roleID, List<UserID> userIDS) {
        List<UserRoleDO> set = new ArrayList<>(userIDS.size());
        for (UserID userId : userIDS) {
            set.add(UserRoleDO.builder().roleId(roleID.getValue())
                    .userId(userId.getValue()).build());
        }

        return userRoleMapper.batchInsert(set) == set.size();
    }

    @Override
    public boolean deleteRef(RoleID roleID, List<UserID> userIDS) {
        List<UserRoleDO> set = new ArrayList<>(userIDS.size());
        for (UserID userId : userIDS) {
            set.add(UserRoleDO.builder().roleId(roleID.getValue())
                    .userId(userId.getValue()).build());
        }

        return userRoleMapper.delete(new LambdaQueryWrapper<UserRoleDO>()
                .eq(UserRoleDO::getRoleId,roleID.getValue())
                .in(UserRoleDO::getUserId, (Object) userIDS.stream().map(UserID::getValue).toArray(Long[]::new))) == set.size();
    }
}
