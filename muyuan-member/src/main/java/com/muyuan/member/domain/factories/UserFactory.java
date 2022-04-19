package com.muyuan.member.domain.factories;

import com.muyuan.member.domain.entity.UserEntity;
import com.muyuan.member.interfaces.dto.RegisterDTO;

public class UserFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static UserEntity newUserEntity(RegisterDTO registerDTO)  {
        UserEntity userEntity = new UserEntity(registerDTO.getUsername(), registerDTO.getPassword());
        return userEntity;
    }
}
