package com.muyuan.member.domains.factories;

import com.muyuan.member.domains.model.User;
import com.muyuan.member.domains.dto.RegisterDTO;

public class UserFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static User newUserEntity(RegisterDTO registerDTO)  {
        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(registerDTO.getPassword())
                .phone(registerDTO.getPhone())
                .build();
        return user;
    }
}
