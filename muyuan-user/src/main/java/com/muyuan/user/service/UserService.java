package com.muyuan.user.service;

import com.muyuan.user.dto.UserVO;

import java.util.Optional;

/**
 * @ClassName UserService
 * Description UserService
 * @Author 2456910384
 * @Date 2022/10/11 14:30
 * @Version 1.0
 */
public interface UserService {

    Optional<UserVO> get();
}
