package com.muyuan.common.interfaces.facade.controller.impl;

import com.muyuan.common.domains.factories.TokenFactory;
import com.muyuan.common.domains.service.TokenService;
import com.muyuan.common.domains.vo.TokenVO;
import com.muyuan.common.enums.TokenStatus;
import com.muyuan.common.interfaces.facade.controller.TokenController;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;

import java.util.Optional;

/**
 * @ClassName TokenControllerImpl
 * Description TokenController 实现
 * @Author 2456910384
 * @Date 2021/10/14 10:57
 * @Version 1.0
 */
public class TokenControllerImpl implements TokenController {


    @Override
    public Result<TokenVO> getToken() {
        TokenService service = TokenFactory.createRedisTokenService();
        Optional<TokenVO> token = service.createToken();
        return ResultUtil.render(token.get());
    }

    @Override
    public Result<TokenStatus> verify(String token) {
        return null;
    }
}
