//package com.muyuan.member.spi.controller;
//
//import com.muyuan.common.result.Result;
//import com.muyuan.common.result.ResultUtil;
//import com.muyuan.member.dto.AccountLoginDTO;
//import com.muyuan.member.service.LoginService;
//import com.muyuan.member.spi.LoginController;
//import com.muyuan.member.vo.AccountLoginVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class LoginControllerImpl implements LoginController {
//
//    @Autowired
//    private LoginService userService;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public Result accountLogin(AccountLoginDTO loginInfo) {
//        if (!redisTemplate.hasKey("captcha:"+loginInfo.getUuid())) {
//            return ResultUtil.renderFail("验证码过时，请重新获取");
//        }
//        Object captcha = redisTemplate.opsForValue().get("captcha:" + loginInfo.getUuid());
//        if (!loginInfo.getCode().equals(captcha)) {
//            return ResultUtil.renderFail("验证码错误");
//        }
//
//        Optional<AccountLoginVo> login = userService.accountLogin(loginInfo);
//        if (login.isPresent()) {
//           return   ResultUtil.render("登录成功",login.get());
//        }
//
//        return ResultUtil.renderFail("登录失败");
//    }
//
//}
