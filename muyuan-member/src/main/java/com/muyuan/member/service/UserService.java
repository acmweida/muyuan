package com.muyuan.member.service;

import com.muyuan.member.vo.UserVO;

import java.util.Optional;

public interface UserService {

    /**
     * 通过UserNO 获取用户信息
     * @param userNo
     * @return
     */
    Optional<UserVO> getUserInfo(String userNo);
}
