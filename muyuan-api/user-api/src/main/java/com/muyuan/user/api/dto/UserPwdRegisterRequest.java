package com.muyuan.user.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName OperatorUserPwdRegisterRequest
 * Description 运营人员注册 用户名密码方式 参数
 * @Author 2456910384
 * @Date 2022/9/13 17:32
 * @Version 1.0
 */
@Builder
@Data
@NoArgsConstructor
public class UserPwdRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1457932148567l;
}
