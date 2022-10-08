package com.muyuan.user.api.dto;

import com.muyuan.common.core.enums.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName OperatorQueryRequest
 * Description 运营人员用户信息请求参数
 * @Author 2456910384
 * @Date 2022/9/14 9:05
 * @Version 1.0
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryRequest implements Serializable {

    private static final long serialVersionUID = 145793214856l;

    private String username;

    private PlatformType platformType;

    private String phone;

}
