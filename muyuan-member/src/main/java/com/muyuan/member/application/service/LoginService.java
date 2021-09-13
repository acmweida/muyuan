package com.muyuan.member.application.service;

import com.muyuan.member.domain.vo.AccountLoginVo;
import com.muyuan.member.interfaces.facade.dto.AccountLoginDTO;

import java.util.Optional;

public interface LoginService {

    Optional<AccountLoginVo> accountLogin(AccountLoginDTO loginInfo);


}
