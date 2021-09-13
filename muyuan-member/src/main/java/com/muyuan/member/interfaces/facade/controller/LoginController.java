//package com.muyuan.member.interfaces.facade.spi;
//
//import com.muyuan.common.result.Result;
//import com.muyuan.member.interfaces.facade.spi.dto.AccountLoginDTO;
//import com.muyuan.member.interfaces.facade.spi.dto.RegisterDTO;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@RestController()
//@RequestMapping("/login")
//@Api(tags = {"登录接口"})
//public interface LoginController {
//
//
//    @ApiOperation(value = "账号登录接口",code = 0)
//    @PostMapping("/accountLogin")
//    Result accountLogin(@RequestBody @Validated AccountLoginDTO loginInfo);
//
//}
