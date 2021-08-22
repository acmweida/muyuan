package com.muyuan.member.service;

import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.vo.UserVO;

import java.util.Optional;

public interface UserService {

    Optional<UserVO> login(AccountLoginDTO loginInfo);

    int register(RegisterDTO registerInfo);
}
