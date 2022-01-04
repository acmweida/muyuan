package com.muyuan.member.domain.query.impl;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.common.util.EncryptUtil;
import com.muyuan.common.util.IdUtil;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.query.UserQuery;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserQueryImpl implements UserQuery {

    @Autowired
    UserRepo userRepo;

    @Override
    public Optional<User> getUserInfo(String userNo) {
        final User user = userRepo.selectOne(new SqlBuilder(User.class)
                .eq("userNo", userNo)
                .build());
        if (null == user) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        final User user = userRepo.selectOne(new SqlBuilder(User.class)
                .eq("username", username)
                .eq("status",0)
                .build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
