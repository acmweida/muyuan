package com.muyuan.auth.controller.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.auth.controller.LoginController;
import com.muyuan.auth.vo.CaptchaVo;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
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
public class LoginControllerImpl implements LoginController {

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
            return ResultUtil.render();
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Image = encoder.encode(captchaChallengeAsJpeg);
        CaptchaVo captchaVo = new CaptchaVo();
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(LoginMessageConst.CAPTCHA_KEY_PREFIX+uuid,createText);
        redisTemplate.expire(LoginMessageConst.CAPTCHA_KEY_PREFIX+uuid,5, TimeUnit.MINUTES);
        captchaVo.setImg(base64Image);
        captchaVo.setUuid(uuid);
        log.info("验证码：{},key:{}",createText,uuid);

        return ResultUtil.render(captchaVo);
    }
}
