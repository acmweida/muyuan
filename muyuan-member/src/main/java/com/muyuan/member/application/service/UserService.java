package com.muyuan.member.application.service;

import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.facade.dto.RegisterDTO;

import java.util.Optional;

public interface UserService {

    /**
     * 通过UserNO 获取用户信息
     * @param userNo
     * @return
     */
    Optional<UserVO> getUserInfo(String userNo);

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int accountRegister(RegisterDTO registerInfo);
}
