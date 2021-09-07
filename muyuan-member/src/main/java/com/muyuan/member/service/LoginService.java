package com.muyuan.member.service;

import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.vo.AccountLoginVo;

import java.util.Optional;

public interface LoginService {

    Optional<AccountLoginVo> accountLogin(AccountLoginDTO loginInfo);


}
