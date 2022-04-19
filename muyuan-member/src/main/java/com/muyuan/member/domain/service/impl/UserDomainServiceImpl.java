package com.muyuan.member.domain.service.impl;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.member.domain.entity.UserEntity;
import com.muyuan.member.domain.factories.UserFactory;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.query.UserQuery;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.domain.service.UserDomainService;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @ClassName UserDomainServiceImpl
 * Description 用户域服务
 * @Author 2456910384
 * @Date 2022/4/18 15:57
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    private UserQuery userQuery;

    private UserRepo userRepo;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userQuery.get(new User(username));
    }

    @Override
    public Optional<User> getByyId(Long userId) {
        final Optional<User> userInfo = userQuery.get(new User(userId));
        if (!userInfo.isPresent()) {
            return Optional.empty();
        }
        return userInfo;
    }

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    public int add(RegisterDTO registerInfo) {

        User account = userRepo.selectOne(new SqlBuilder(User.class).select("id")
                .eq("username", registerInfo.getUsername())
                .build());
        if (null != account) {
            return 1;
        }

        UserEntity userEntity = UserFactory.newUserEntity(registerInfo);
        userEntity.initInstance();

        userRepo.insert(userEntity);

        if (userRepo.insert(userEntity)) {
            return 0;
        }

        return -1;

    }
}
