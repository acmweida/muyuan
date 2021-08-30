package com.muyuan.member.spi.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.service.UserService;
import com.muyuan.member.spi.LoginController;
import com.muyuan.member.vo.AccountLoginVo;
import com.muyuan.member.vo.CaptchaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class LoginControllerImpl implements LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    DefaultKaptcha kaptcha;

    public Result accountRegister(RegisterDTO register) {
        int registerResult = userService.accountRegister(register);
        if (registerResult == 0) {
          return   ResultUtil.render("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.renderFail("账号已存在");
        }
        return ResultUtil.renderFail("注册失败");
    }


    public Result accountLogin(AccountLoginDTO loginInfo) {
        if (!redisTemplate.hasKey("captcha:"+loginInfo.getUuid())) {
            return ResultUtil.renderFail("验证码过时，请重新获取");
        }
        Object captcha = redisTemplate.opsForValue().get("captcha:" + loginInfo.getUuid());
        if (!loginInfo.getCode().equals(captcha)) {
            return ResultUtil.renderFail("验证码错误");
        }

        Optional<AccountLoginVo> login = userService.accountLogin(loginInfo);
        if (login.isPresent()) {
           return   ResultUtil.render("登录成功",login.get());
        }

        return ResultUtil.renderFail("登录失败");
    }

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
        redisTemplate.opsForValue().set("captcha:"+uuid,createText);
        redisTemplate.expire("captcha:"+uuid,5, TimeUnit.MINUTES);
        captchaVo.setImg(base64Image);
        captchaVo.setUuid(uuid);

        return ResultUtil.render(captchaVo);
    }
}
