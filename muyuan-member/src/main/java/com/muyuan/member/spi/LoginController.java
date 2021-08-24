package com.muyuan.member.spi;

import com.muyuan.common.result.Result;
import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@Api(tags = {"用户接口"})
public interface LoginController {

    @PostMapping("/accountRegister")
    Result accountRegister(@RequestBody @Validated  RegisterDTO register);

    @ApiOperation(value = "账号登录接口",code = 0)
    @PostMapping("/accountLogin")
    Result accountLogin(@RequestBody @Validated AccountLoginDTO loginInfo);
}
