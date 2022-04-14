package com.muyuan.common.member.application.query;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.member.domain.entity.UserEntity;
import com.muyuan.common.member.domain.model.User;
import com.muyuan.common.member.domain.repo.UserRepo;
import com.muyuan.common.member.interfaces.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserQuery {

    private UserRepo userRepo;

    /**
     * 通过UserNO 获取用户信息
     * @param userId
     * @return
     */
    public Optional<User> getUserInfo(Long userId) {
        final User user = userRepo.selectOne(new SqlBuilder(User.class)
                .eq("id", userId)
                .eq("status",0)
                .build());
        if (null == user) {
            return Optional.empty();
        }

        return Optional.of(user);
    }


    /**
     * 通过Uaccount 获取用户信息
     * @param username
     * @return
     */
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

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
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
        user.setNickName(UserEntity.createUserName());
        user.setPassword(EncryptUtil.SHA1(registerInfo.getPassword() + salt, encryptKey));;
        user.setSalt(salt);
        user.setEncryptKey(encryptKey);

        userRepo.insert(user);

        return 0;
    }
}
