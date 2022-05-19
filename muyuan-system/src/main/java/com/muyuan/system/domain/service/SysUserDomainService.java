package com.muyuan.system.domain.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.interfaces.dto.SysUserDTO;

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
     * 列表查询
     * @param sysUserDTO
     * @return
     */
    Page<SysUser> list(SysUserDTO sysUserDTO);

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
     * 系统用户新增
     * @param sysUserDTO
     * @return
     */
    void add(SysUserDTO sysUserDTO);

    /**
     * 检查唯一性
     * @param sysUser
     * @return
     */
    String checkAccountNameUnique(SysUser sysUser);
}
