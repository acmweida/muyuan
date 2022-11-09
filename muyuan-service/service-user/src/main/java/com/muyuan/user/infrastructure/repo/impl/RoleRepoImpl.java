package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.face.dto.RoleQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import lombok.AllArgsConstructor;
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
}
