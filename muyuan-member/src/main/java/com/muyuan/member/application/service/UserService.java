package com.muyuan.member.application.service;

import com.muyuan.member.interfaces.dto.UserDTO;

public interface UserService {

    UserDTO getUserByUsername(String username);
}
