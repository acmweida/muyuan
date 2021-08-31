package com.muyuan.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;

public class JWTUtil {


    private  String singingKey = "jfd2fdsfs923m0-4dfs3.ef121d-asda";

    public JWTUtil(String singingKey) {
        this.singingKey = singingKey;
    }

    public  String createJwt(Date exp, Map claims) {
        DateTime now = DateTime.now();
        String token = JWT.create()
                .withIssuedAt(now.toDate())
                .withExpiresAt(exp)
                .withPayload(claims)
                .sign(Algorithm.HMAC256(singingKey));
        return token;
    }

    public  Map parseToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(singingKey)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        Map<String, Claim> claims = verify.getClaims();
        return claims;
    }

}
