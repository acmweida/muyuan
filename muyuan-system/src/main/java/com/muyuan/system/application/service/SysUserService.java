package com.muyuan.system.application.service;

import com.muyuan.system.interfaces.dto.SysUserDTO;

public interface SysUserService {

    SysUserDTO getUserByUsername(String username);
}
