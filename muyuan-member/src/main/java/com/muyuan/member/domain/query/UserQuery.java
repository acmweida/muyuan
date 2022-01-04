package com.muyuan.member.domain.query;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.interfaces.dto.RegisterDTO;

import java.util.Optional;

public interface UserQuery {

    /**
     * 通过UserNO 获取用户信息
     * @param userNo
     * @return
     */
    Optional<User> getUserInfo(String userNo);

    /**
     * 通过Uaccount 获取用户信息
     * @param username
     * @return
     */
    Optional<User> getUserByUsername(String username);

}
