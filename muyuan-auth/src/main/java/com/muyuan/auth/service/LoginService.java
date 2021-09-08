package com.muyuan.auth.service;


import com.muyuan.auth.dto.AccountLoginDTO;
import com.muyuan.auth.vo.AccountLoginVo;

import java.util.Optional;

public interface LoginService {

    Optional<AccountLoginVo> accountLogin(AccountLoginDTO loginInfo);


}
