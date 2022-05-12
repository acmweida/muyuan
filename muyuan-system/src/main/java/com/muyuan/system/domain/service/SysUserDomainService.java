package com.muyuan.system.domain.service;

import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.interfaces.dto.RegisterDTO;

import java.util.Optional;

/**
 * @ClassName SysUserDomainService 接口
 * Description 系统用户域服务
 * @Author 2456910384
 * @Date 2022/4/18 11:45
 * @Version 1.0
 */
public interface SysUserDomainService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    Optional<SysUser> getByyUsername(String  username);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    Optional<SysUser> getByyId(Long  userId);


    /**
     * 账户注册
     * 0-注册成功
     * @param registerInfo
     * @return
     */
    int add(RegisterDTO registerInfo);

    /**
     * 检查唯一性
     * @param sysUser
     * @return
     */
    String checkAccountNameUnique(SysUser sysUser);
}
