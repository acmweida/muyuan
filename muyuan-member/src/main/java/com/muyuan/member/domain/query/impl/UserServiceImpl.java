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
public class UserServiceImpl implements UserQuery {

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
                .build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public int accountRegister(RegisterDTO registerInfo) {

        User account = userRepo.selectOne(new SqlBuilder(User.class).select("id")
                .eq("username", registerInfo.getUsername())
                .build());
        if (null != account) {
            return 1;
        }

        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        User user = new User();
        BeanUtils.copyProperties(registerInfo,user);
        user.setNickName(IdUtil.createUserName());
        user.setPassword(EncryptUtil.SHA1(registerInfo.getPassword() + salt, encryptKey));;
        user.setSalt(salt);
        user.setEncryptKey(encryptKey);

        userRepo.insert(user);

        return 0;
    }
}
