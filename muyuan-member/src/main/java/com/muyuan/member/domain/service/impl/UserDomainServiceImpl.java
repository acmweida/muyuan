package com.muyuan.member.domain.service.impl;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.query.UserQuery;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.domain.service.UserDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    private UserQuery sysUserQuery;

    private UserRepo userRepo;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return sysUserQuery.get(new User(username));
    }
}
