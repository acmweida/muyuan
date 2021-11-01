package com.muyuan.gateway.base.component;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.time.Instant;

@Component
public class MuyuanReactiveJwtDecoder implements ReactiveJwtDecoder {
    @Override
    public Mono<Jwt> decode(String token) throws JwtException {
        Jwt jwt = null;
        try {
            SignedJWT sjwt = SignedJWT.parse(token);
            jwt = new Jwt(token,
                    Instant.parse(sjwt.getJWTClaimsSet().getIssuer()),
                    sjwt.getJWTClaimsSet().getExpirationTime().toInstant(),
                    sjwt.getHeader().getCustomParams(),
                    sjwt.getJWTClaimsSet().getClaims());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Mono.just(jwt);
    }
}
