package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.UserRepo;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.converter.UserConverterImpl;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.UserDO;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import com.muyuan.user.infrastructure.repo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @ClassName OperatorRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:28
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class UserRepoImpl implements UserRepo {

    private static final UserConverter converter = new UserConverterImpl();

    private UserMapper mapper;
    
    private RoleMapper roleMapper;

    @Override
    public User selectOneByUsername(Username username, PlatformType platformType) {
        UserDO userDO = mapper.selectOne(new SqlBuilder(UserDO.class)
                .eq(UserMapper.USERNAME, username.getValue())
                .eq(UserMapper.STATUS, UserMapper.STATUS_OK)
                .build());
        User user = converter.to(userDO);
        if (null != userDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(userDO.getId(),platformType.getCode());
            user.setRoles(converter.toRole(roleDOS));
        }

        return user;
    }

    @Override
    public User selectOneByID(UserID userID, PlatformType platformType) {
        UserDO userDO = mapper.selectOne(new SqlBuilder(UserDO.class)
                .eq(UserMapper.ID, userID.getValue())
                .eq(UserMapper.STATUS, UserMapper.STATUS_OK)
                .build());
        User user = converter.to(userDO);
        if (null != userDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(userDO.getId(),platformType.getCode());
            user.setRoles(converter.toRole(roleDOS));
        }

        return user;
    }
}
