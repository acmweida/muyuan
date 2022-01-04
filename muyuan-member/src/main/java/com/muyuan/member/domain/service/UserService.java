package com.muyuan.member.domain.service;

import com.muyuan.member.interfaces.dto.RegisterDTO;

public interface UserService {

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int accountRegister(RegisterDTO registerInfo);
}
