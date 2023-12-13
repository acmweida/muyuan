package com.muyuan.auth.util;

import com.muyuan.common.core.util.JSONUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {

    private static final String JWT_PAYLOAD_USER_KEY = "user";


    private final KeyPair keyPair;

    public JwtUtils(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    /**
     * JWT
     * 使用 RSA 算法 生成 id_token
     * 以及对其进行校验(verify)
     * 需要公私钥对
     * <p/>
     * 支持算法
     * RS256
     * RS384
     * RS512
     *
     * @throws Exception
     */
    private String generateToken(JWTClaimsSet claimsSet) {

        // RSA keyPair Generator
//        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        /**
         * 长度 至少 1024, 建议 2048
         */
//        final int keySize = 2048;
//        keyPairGenerator.initialize(keySize);

        //公钥
        final RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        final PrivateKey privateKey = keyPair.getPrivate();

        //keyId
        String keyId = RandomStringUtils.random(32);

        //生成id_token
        JWSSigner jwsSigner = new RSASSASigner(privateKey);

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS512).keyID(keyId).build();

        final String payloadText = "I am MyOIDC [RSA]";


        SignedJWT signedJWT = new SignedJWT(header, claimsSet);

        try {
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        final String idToken = signedJWT.serialize();

        return idToken;

    }

    /**
     * JWT
     * 使用 RSA 算法 生成 id_token
     * 以及对其进行校验(verify)
     * 需要公私钥对
     * <p/>
     * 支持算法
     * RS256
     * RS384
     * RS512
     *
     * @throws Exception
     */
    public boolean verifyToken(String idToken) {

        //公钥
        final RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //校验 id_token

        try {

            final SignedJWT parseJWT = SignedJWT.parse(idToken);

            JWSVerifier verifier = new RSASSAVerifier(publicKey);

            return parseJWT.verify(verifier);
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param expire     过期时间，单位分钟
     * @return JWT
     */
    public String generateToken(Object userInfo, long expire) {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("subject")
                .issuer("Issuer")
                .audience("Audience")
                .claim("payloadText", userInfo)
                .expirationTime(new Date(expire))
                .build();

        return generateToken(claimsSet);
    }


    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @return Jws<Claims>
     */
    private static JWTClaimsSet parserToken(String token) {
        try {
            return  SignedJWT.parse(token).getJWTClaimsSet();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @return 用户信息
     */
    public static  <T> Object getInfoFromToken(String token, Class<T> userType) throws ParseException {
        JWTClaimsSet claimsJws = parserToken(token);
        Map<String, Object> body = claimsJws.getClaims();
        return JSONUtil.parseObject(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType);
    }



}
