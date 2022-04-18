package com.muyuan.member.domain.query;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.member.domain.entity.UserEntity;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserQuery {

    private UserRepo userRepo;

    /**
     * 通过UserNO 获取用户信息
     * @param user
     * @return
     */
    public Optional<User> get(User user) {
        Assert.isTrue(user != null,"sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(User.class);
        if (ObjectUtils.isNotEmpty(user.getId())) {
            sqlBuilder.eq("id", user.getId());
        }
        if (ObjectUtils.isNotEmpty(user.getUsername())) {
            sqlBuilder.eq("username", user.getUsername());
        }
        sqlBuilder.eq("status",0);

        user = userRepo.selectOne(sqlBuilder.build());
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
