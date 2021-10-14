package com.muyuan.common.domains.service;

import com.muyuan.common.domains.vo.TokenVO;

import java.util.Optional;

/**
 * @ClassName TokenService
 * Description Token域服务
 * @Author 2456910384
 * @Date 2021/10/14 11:02
 * @Version 1.0
 */
public interface TokenService {

    Optional<TokenVO> createToken();

}
