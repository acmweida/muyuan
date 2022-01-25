package com.muyuan.common.domains.manager.impl;

import com.muyuan.common.domains.manager.TokenManager;

import java.util.UUID;

/**
 * @ClassName UUIDTokenManager
 * Description TODO
 * @Author 2456910384
 * @Date 2021/10/14 11:36
 * @Version 1.0
 */
public class UUIDTokenManager implements TokenManager {
    @Override
    public String createToken() {
        return UUID.randomUUID().toString();
    }
}
