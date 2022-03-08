package com.muyuan.member.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.member.interfaces.dto.UserDTO;


public interface UserInterface {

    /**
     *  通过账号获取用户信息
     * @param username 用户名
     * @return
     */
    Result<UserDTO> getUserByUsername(String username);

}
