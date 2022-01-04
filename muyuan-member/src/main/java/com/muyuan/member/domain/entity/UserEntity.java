package com.muyuan.member.domain.entity;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.common.util.EncryptUtil;
import com.muyuan.common.util.IdUtil;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class UserEntity extends User  {



    private UserRepo userRepo;

    public UserEntity(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public int accountRegister(RegisterDTO registerInfo) {

        User account = userRepo.selectOne(new SqlBuilder(User.class).select("id")
                .eq("username", registerInfo.getUsername())
                .build());
        if (null != account) {
            return 1;
        }

        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        BeanUtils.copyProperties(registerInfo,this);
        setNickName(IdUtil.createUserName());
        setPassword(EncryptUtil.SHA1(registerInfo.getPassword() + salt, encryptKey));;
        setSalt(salt);
        setEncryptKey(encryptKey);

        userRepo.insert(this);

        return 0;
    }
}
