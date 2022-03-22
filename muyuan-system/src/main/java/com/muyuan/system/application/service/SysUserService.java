package com.muyuan.system.application.service;

import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import com.muyuan.system.interfaces.dto.SysUserDTO;

import java.util.Optional;

/**
 * 用户应用接口
 */
public interface SysUserService {

    /**
     * 登录获取用户信息 内部RPC
     * @param username
     * @return
     */
    SysUserDTO getUserByUsername(String username);

    /**
     * 获取用户信息
     * @return
     */
    Optional<SysUserVO> getUserInfo();

    /**
     * 账户注册
     * 0-注册成功 1-账户已存在
     * @param registerInfo
     * @return
     */
    int add(RegisterDTO registerInfo);
}
