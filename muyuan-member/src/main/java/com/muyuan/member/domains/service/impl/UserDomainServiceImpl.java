package com.muyuan.member.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.member.domains.model.Role;
import com.muyuan.member.domains.model.User;
import com.muyuan.member.domains.repo.UserRepo;
import com.muyuan.member.domains.service.UserDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     *
     * @param user
     * @return
     */
    public void add(User user) {

        if (GlobalConst.NOT_UNIQUE.equals(checkAccountNameUnique(new User(user.getUsername())))) {
            throw new MuyuanException(ResponseCode.ADD_EXIST.getCode(), "账号名已存在");
        }

        if (GlobalConst.NOT_UNIQUE.equals(checkAccountNameUnique(User.builder()
                .phone(user.getPhone())
                .build()))) {
            throw new MuyuanException(ResponseCode.ADD_EXIST.getCode(), "手机号已存在");
        }

        user.initInstance();

        user.save(userRepo);
    }

    @Override
    public void addRole(User user, Role role) {
        user.addRole(userRepo,role);
    }

    /**
     * 通过UserNO 获取用户信息
     *
     * @param sysUser
     * @return
     */
    public Optional<User> get(User sysUser) {
        Assert.isTrue(sysUser != null, "sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(User.class)
                .eq("id", sysUser.getId())
                .eq("username", sysUser.getUsername())
                .eq("status", 0);

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
                .eq("phone", sysUser.getPhone())
                .build());
        if (null != account && !id.equals(account.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }
}
