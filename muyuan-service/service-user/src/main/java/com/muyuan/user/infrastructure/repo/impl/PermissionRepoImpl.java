package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
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

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;
import static com.muyuan.user.infrastructure.repo.mapper.PermissionMapper.*;

@Component
@AllArgsConstructor
public class PermissionRepoImpl implements PermissionRepo {

    private PermissionMapper permissionMapper;

    private PermissionConverter converter;

    @Override
    public List<Permission> selectByRoles(RoleID roleId){
        List<PermissionDO> permissionDOS = permissionMapper.selectByRoleId(roleId.getValue());
        return converter.to(permissionDOS);
    }

    @Override
    public Page<Permission> select(PermissionQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(PermissionDO.class)
                .eq(BUSINESS, command.getBusiness())
                .eq(MODULE, command.getModule())
                .eq(STATUS, command.getStatus())
                .eq(TYPE, command.getType())
                .like(RESOURCE,command.getResource())
                .eq(PLATFORM_TYPE, ObjectUtils.isEmpty(command.getPlatformType()) ? null : command.getPlatformType().getCode())
                .orderByDesc(BUSINESS, MODULE);

        Page<Permission> page = Page.<Permission>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<PermissionDO> list = permissionMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }
}
