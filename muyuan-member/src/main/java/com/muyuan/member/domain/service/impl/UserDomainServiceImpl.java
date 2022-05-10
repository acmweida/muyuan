package com.muyuan.member.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.member.domain.entity.UserEntity;
import com.muyuan.member.domain.factories.UserFactory;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.domain.service.UserDomainService;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

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

    private UserRepo userRepo;

    @Override
    public Optional<User> getUserByUsername(String username) {
        final Optional<User> userInfo = get(new User(username));
        if (!userInfo.isPresent()) {
            return Optional.empty();
        }
        return userInfo;
    }

    @Override
    public Optional<User> getByyId(Long userId) {
        final Optional<User> userInfo = get(new User(userId));
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

    /**
     * 通过UserNO 获取用户信息
     * @param sysUser
     * @return
     */
    public Optional<User> get(User sysUser) {
        Assert.isTrue(sysUser != null,"sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(User.class);
        if (ObjectUtils.isNotEmpty(sysUser.getId())) {
            sqlBuilder.eq("id", sysUser.getId());
        }
        if (ObjectUtils.isNotEmpty(sysUser.getUsername())) {
            sqlBuilder.eq("username", sysUser.getUsername());
        }
        sqlBuilder.eq("status",0);

        final User user = userRepo.selectOne(sqlBuilder.build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public String checkAccountNameUnique(User sysUser) {
        Long id = null == sysUser.getId() ? 0 : sysUser.getId();
        User account = userRepo.selectOne(new SqlBuilder(User.class).select("id")
                .eq("username", sysUser.getUsername())
                .build());
        if (null != account && !id.equals(account.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }
}
