package com.muyuan.user.api;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.api.dto.UserDTO;

/**
 * @ClassName OperatorInterface 接口
 * Description 员信息接口
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
public interface UserInterface   {

    /**
     * 通过用户名称 获取用户信息
     * 默认用户类型 MEMBER
     * @param username
     * @param platformType
     * @return
     */
    Result<UserDTO> getUserByUsername(String username, PlatformType platformType);

}
