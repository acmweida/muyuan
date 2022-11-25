package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.face.dto.RoleQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;

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

    @Override
    public List<Role> selectRoleByUserId(UserID userId, PlatformType platformType) {
        List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(userId.getValue(), platformType.getCode());
        return converter.toRole(roleDOS);
    }

    @Override
    public Page<Role> select(RoleQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(RoleDO.class)
                .eq(NAME, command.getName())
                .eq(STATUS, command.getStatus())
                .eq(PLATFORM_TYPE,command.getPlatformType().getCode())
                .orderByAsc(ORDER_NUM);

        Page<Role> page = Page.<Role>builder().build();
        if (command.enablePage()) {
            page.setPageNum(command.getPageNum());
            page.setPageSize(command.getPageSize());
            sqlBuilder.page(page);
        }

        List<RoleDO> list = roleMapper.selectList(sqlBuilder.build());

        page.setRows(converter.toRole(list));

        return page;
    }

    @Override
    public Role select(Long id) {
        RoleDO roleDO = roleMapper.selectOne(new SqlBuilder(RoleDO.class)
                .eq(ID, id)
                .build());
        return converter.toRole(roleDO);
    }

    @Override
    public Role selectRole(Role.Identify identify) {
        RoleDO roleDO = roleMapper.selectOne(new SqlBuilder(RoleDO.class).select(ID)
                .eq(ID, identify.getId().getValue())
                .eq(PLATFORM_TYPE, identify.getPlatformType().getCode())
                .eq(CODE,identify.getCode())
                .build());

        return converter.toRole(roleDO);
    }

    @Override
    public List<Role> selectByMenuID(MenuID menuId) {
        if (ObjectUtils.isEmpty(menuId)) {
            return GlobalConst.EMPTY_LIST;
        }
        return converter.toRole(roleMapper.selectRoleByMenuID(menuId.getValue()));
    }

    @Override
    public List<Role> selectByPermID(Long permId) {
        if (ObjectUtils.isEmpty(permId)) {
            return GlobalConst.EMPTY_LIST;
        }
        return converter.toRole(roleMapper.selectRoleByMenuID(permId));
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
        Integer count = roleMapper.insertAuto(to);
        role.setId(new RoleID(to.getId()));
        return count > 0;
    }

    @Override
    public Role updateRole(Role role) {
        SqlBuilder sqlBuilder = new SqlBuilder(RoleDO.class)
                .eq(ID, role.getId().getValue());

        RoleDO roleDO = roleMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(roleDO)) {
            roleMapper.updateBy(converter.to(role), ID);
        }

        return converter.toRole(roleDO);
    }

    @Override
    public List<Role> deleteBy(Long... ids) {
        List<RoleDO> roleDOS = roleMapper.selectList(new SqlBuilder(RoleDO.class)
                .in(ID, ids)
                .build());

        roleMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.toRole(roleDOS);
    }
}
