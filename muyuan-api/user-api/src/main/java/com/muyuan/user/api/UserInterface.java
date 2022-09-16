package com.muyuan.user.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.api.dto.UserQueryRequest;

/**
 * @ClassName OperatorInterface 接口
 * Description 用户信息接口
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
public interface UserInterface {

    /**
     * 通过用户名称 获取用户信息
     * 默认用户类型 MEMBER
     * @param request
     * @return
     */
    Result<UserDTO> getUserByUsername(UserQueryRequest request);

}
