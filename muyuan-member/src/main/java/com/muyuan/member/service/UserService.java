package com.muyuan.member.service;

import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.vo.UserVO;

import java.util.Optional;

public interface UserService {

    Optional<UserVO> login(AccountLoginDTO loginInfo);

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int register(RegisterDTO registerInfo);
}
