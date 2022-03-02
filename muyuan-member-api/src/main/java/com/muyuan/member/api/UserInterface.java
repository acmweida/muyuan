package com.muyuan.member.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.member.interfaces.dto.UserDTO;


public interface UserInterface {

    Result<UserDTO> getUserByUsername(String username);

}
