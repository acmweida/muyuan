package com.muyuan.manager.auth.controller.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.auth.controller.AuthController;
import com.muyuan.manager.auth.vo.CaptchaVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AuthControllerImpl implements AuthController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    DefaultKaptcha kaptcha;

    public Result captchaImage(HttpServletRequest httpServletRequest) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String createText = kaptcha.createText();
        try {
            //生产验证码字符串并保存到session中
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = kaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            return ResultUtil.success();
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Image = encoder.encode(captchaChallengeAsJpeg);
        CaptchaVo captchaVo = new CaptchaVo();
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(GlobalConst.CAPTCHA_KEY_PREFIX+uuid,createText);
        redisTemplate.expire(GlobalConst.CAPTCHA_KEY_PREFIX+uuid,5, TimeUnit.MINUTES);
        captchaVo.setImg(base64Image);
        captchaVo.setUuid(uuid);
        log.info("验证码：{},key:{}",createText,uuid);

        return ResultUtil.success(captchaVo);
    }

    @Override
    public Result logout() {
        JsonNode payload = JwtUtils.getJwtPayload();
        String jti = payload.get(SecurityConst.JWT_JTI).asText(); // JWT唯一标识
        Long expireTime = payload.get(SecurityConst.JWT_EXP).asLong(); // JWT过期时间戳(单位：秒)
        if (expireTime != null) {
            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                redisTemplate.opsForValue().set(SecurityConst.TOKEN_BLACKLIST_PREFIX + jti, null, (expireTime - currentTime), TimeUnit.SECONDS);
            }
        } else { // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(SecurityConst.TOKEN_BLACKLIST_PREFIX + jti, null);
        }
        log.info("用户：{} 注销登录", SecurityUtils.getUserId());
        return ResultUtil.success("注销成功");
    }
}
