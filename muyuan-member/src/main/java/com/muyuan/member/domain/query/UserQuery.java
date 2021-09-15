package com.muyuan.member.domain.query;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.vo.UserVO;
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
     * @param account
     * @return
     */
    Optional<User> getUserByAccount(String account);

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int accountRegister(RegisterDTO registerInfo);
}
