package com.muyuan.system.application;

import com.muyuan.system.interfaces.dto.SysUserDTO;

public interface SysUserService {

    SysUserDTO getUserByUsername(String username);
}
