package com.muyuan.member.base.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.muyuan.common.util.JWTUtil;
import com.muyuan.member.base.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTManager {

    JwtConfig config;

    JWTUtil jwtUtil;

    @Autowired
    public JWTManager(JwtConfig config) {
        jwtUtil = new JWTUtil(config.getSigningKey());
        this.config = config;
    }

    public String createJwt(Date expireTime, Map claims) {
      return   jwtUtil.createJwt(expireTime, claims);
    }

    public  Map parseToken(String token) {
        return jwtUtil.parseToken(token);
    }


}
