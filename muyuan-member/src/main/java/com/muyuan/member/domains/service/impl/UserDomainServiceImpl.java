package com.muyuan.member.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.member.domains.model.Role;
import com.muyuan.member.domains.model.User;
import com.muyuan.member.domains.repo.UserRepo;
import com.muyuan.member.domains.service.UserDomainService;
import com.muyuan.member.infrastructure.persistence.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

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
        return get(new User(username));
    }

    @Override
    public Optional<User> getByyId(Long userId) {
        return get(new User(userId));
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
        user.addRole(userRepo, role);
    }

    /**
     * 通过UserNO 获取用户信息
     *
     * @param user
     * @return
     */
    public Optional<User> get(User user) {
        Assert.isTrue(user != null, "sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(User.class)
                .eq(UserMapper.ID, user.getId())
                .eq("username", user.getUsername())
                .eq(UserMapper.STATUS, 0);

        return Optional.ofNullable(userRepo.selectOne(sqlBuilder.build()));
    }

    @Override
    public String checkAccountNameUnique(User user) {
        Long id = null == user.getId() ? 0 : user.getId();
        User account = userRepo.selectOne(new SqlBuilder(User.class).select("id")
                .eq("username", user.getUsername())
                .eq("phone", user.getPhone())
                .build());
        if (null != account && !id.equals(account.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public void linkShop(Long shopId) {
        Assert.notNull(shopId,"shopId is null");
        Optional<User> user = get(User.builder()
                .id(SecurityUtils.getUserId()).build());
        if (!ObjectUtils.isEmpty(user.get().getShopId())) {
            throw new MuyuanException(ResponseCode.FAIL.getCode(), "该账户已绑定店铺");
        }

        user.get().setShopId(shopId);
        user.get().save(userRepo);
    }
}
