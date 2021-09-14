package com.muyuan.auth.controller.impl;

import com.muyuan.auth.controller.KeyPairController;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@Component
public class KeyPairControllerImpl implements KeyPairController {

    private final KeyPair keyPair;

    public KeyPairControllerImpl(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public Map getkey() {
        RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(aPublic).build();
        return new JWKSet(key).toJSONObject();
    }
}
